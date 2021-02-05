package com.demo.nerdeyesem.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.demo.nerdeyesem.R
import com.demo.nerdeyesem.presentation.extensions.showError

class LoginFragment : BiometricFragment() {

    override fun onAuthError(errorCode: Int, errString: CharSequence) {
        requireView().showError(errString.toString())
    }

    override fun onAuthSucceeded() {
        val action = LoginFragmentDirections.actionLoginToRestaurants()
        Navigation.findNavController(requireView()).navigate(action)
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

    private fun initView(view: View) {
        view.findViewById<View>(R.id.login).setOnClickListener {
            //Login
        }
        view.findViewById<View>(R.id.biometric).setOnClickListener {
            authenticate()
        }
    }
}