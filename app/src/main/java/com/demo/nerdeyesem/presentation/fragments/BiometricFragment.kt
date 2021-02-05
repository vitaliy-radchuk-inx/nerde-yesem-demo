package com.demo.nerdeyesem.presentation.fragments

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.demo.nerdeyesem.R
import com.demo.nerdeyesem.presentation.extensions.showError
import java.util.concurrent.Executor

abstract class BiometricFragment : Fragment() {

    companion object {
        internal const val RC_BIOMETRIC_ENROLL = 10002
        internal const val BIOMETRIC_NOT_SET_MESSAGE_DELAY = 1000L
    }

    private lateinit var executor: Executor
    private lateinit var biometricPrompt: BiometricPrompt
    private lateinit var promptInfo: BiometricPrompt.PromptInfo

    abstract fun onAuthError(errorCode: Int, errString: CharSequence)
    abstract fun onAuthSucceeded()
    abstract fun onAuthFailed()

    protected fun authenticate() {
        val manager = BiometricManager.from(requireContext())
        when (manager.canAuthenticate(BiometricManager.Authenticators.BIOMETRIC_STRONG)) {
            BiometricManager.BIOMETRIC_SUCCESS -> {
                biometricPrompt.authenticate(promptInfo)
            }
            BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE -> {
                requireView().showError(R.string.error_no_biometric_features_available)
            }
            BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE -> {
                requireView().showError(R.string.error_biometric_features_unavailable)
            }
            BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED -> {
                requireView().run {
                    showError(R.string.error_biometric_features_not_set)
                    postDelayed({ showBiometricSettings() }, BIOMETRIC_NOT_SET_MESSAGE_DELAY)
                }
            }
            else -> {
                requireView().showError(R.string.error_unknown)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBiometricPrompt()
    }

    private fun initBiometricPrompt() {
        executor = ContextCompat.getMainExecutor(requireContext())

        biometricPrompt = BiometricPrompt(this, executor,
            object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                    super.onAuthenticationError(errorCode, errString)
                    onAuthError(errorCode, errString)
                }

                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                    super.onAuthenticationSucceeded(result)
                    onAuthSucceeded()
                }

                override fun onAuthenticationFailed() {
                    super.onAuthenticationFailed()
                    onAuthFailed()
                }
            })

        promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle(getString(R.string.fragment_biometric_verify_your_identity))
            .setSubtitle(getString(R.string.fragment_biometric_scan_your_fingerprint))
            .setNegativeButtonText(getString(R.string.fragment_biometric_use_password))
            .build()
    }

    private fun showBiometricSettings() {
        val intent = Intent(Settings.ACTION_BIOMETRIC_ENROLL).apply {
            putExtra(
                Settings.EXTRA_BIOMETRIC_AUTHENTICATORS_ALLOWED,
                BiometricManager.Authenticators.BIOMETRIC_STRONG
            )
        }
        startActivityForResult(intent, RC_BIOMETRIC_ENROLL)
    }
}