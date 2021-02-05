package com.demo.nerdeyesem.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.ProgressBar
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.demo.nerdeyesem.R
import com.demo.nerdeyesem.presentation.extensions.hideKeyboard
import com.demo.nerdeyesem.presentation.extensions.showError
import com.demo.nerdeyesem.presentation.viewmodels.LoginViewModel
import com.demo.nerdeyesem.presentation.viewmodels.LoginViewModelFactory
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth

class LoginFragment : BiometricFragment() {

    private val viewModelFactory by lazy {
        LoginViewModelFactory(FirebaseAuth.getInstance())
    }
    private val viewModel: LoginViewModel by viewModels { viewModelFactory }

    private lateinit var progress: ProgressBar
    private lateinit var emailContainer: TextInputLayout
    private lateinit var email: TextInputEditText
    private lateinit var passwordContainer: TextInputLayout
    private lateinit var password: TextInputEditText
    private lateinit var login: MaterialButton

    override fun onAuthError(errorCode: Int, errString: CharSequence) {
        requireView().showError(errString.toString())
    }

    override fun onAuthSucceeded() {
        navigateToRestaurants()
    }

    override fun onAuthFailed() {
        requireView().showError(R.string.error_auth_failed)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_login, container, false)
        initView(view)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.emailError().observe(viewLifecycleOwner, { isError ->
            login.isEnabled = true
            emailContainer.error = when (isError) {
                true -> getString(R.string.error_invalid_email)
                else -> null
            }
        })
        viewModel.passwordError().observe(viewLifecycleOwner, { isError ->
            login.isEnabled = true
            passwordContainer.error = when (isError) {
                true -> getString(R.string.error_invalid_password)
                else -> null
            }
        })
        viewModel.showProgress().observe(this.viewLifecycleOwner, { showProgress ->
            progress.isVisible = showProgress ?: false
        })
        viewModel.showError().observe(this.viewLifecycleOwner, { message ->
            login.isEnabled = true
            message?.let { requireView().showError(message) }
        })
        viewModel.loginSuccess().observe(this.viewLifecycleOwner, {
            login.isEnabled = true
            navigateToRestaurants()
        })
    }

    private fun initView(view: View) {
        progress = view.findViewById(R.id.progress)
        emailContainer = view.findViewById(R.id.emailContainer)
        email = view.findViewById(R.id.email)
        passwordContainer = view.findViewById(R.id.passwordContainer)
        password = view.findViewById(R.id.password)
        password.setOnEditorActionListener { _, actionId, _ ->
            hideKeyboard()
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                tryLogin()
            }
            true
        }
        login = view.findViewById(R.id.login)
        login.setOnClickListener {
            hideKeyboard()
            tryLogin()
        }
        view.findViewById<View>(R.id.biometric).setOnClickListener {
            authenticate()
        }
    }

    private fun tryLogin() {
        login.isEnabled = false
        viewModel.login(email.text?.trim().toString(), password.text?.trim().toString())
    }

    private fun navigateToRestaurants() {
        val action = LoginFragmentDirections.actionLoginToRestaurants()
        Navigation.findNavController(requireView()).navigate(action)
    }
}