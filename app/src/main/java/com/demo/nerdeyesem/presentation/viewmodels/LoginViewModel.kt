package com.demo.nerdeyesem.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

class LoginViewModel(private val auth: FirebaseAuth) : ViewModel() {

    private val showProgressLiveData = MutableLiveData<Boolean>()
    private val errorMessageLiveData = MutableLiveData<String>()
    private val emailErrorLiveData = MutableLiveData<Boolean>()
    private val passwordErrorLiveData = MutableLiveData<Boolean>()
    private val loginSuccessLiveData = MutableLiveData<Boolean>()

    fun showProgress(): LiveData<Boolean> = showProgressLiveData

    fun showError(): LiveData<String> = errorMessageLiveData

    fun emailError(): LiveData<Boolean> = emailErrorLiveData

    fun passwordError(): LiveData<Boolean> = passwordErrorLiveData

    fun loginSuccess(): LiveData<Boolean> = loginSuccessLiveData

    fun login(email: String, password: String) {
        if (email.isEmpty()) {
            emailErrorLiveData.value = true
            return
        }
        emailErrorLiveData.value = null
        if (password.isEmpty()) {
            passwordErrorLiveData.value = true
            return
        }
        passwordErrorLiveData.value = null

        showProgressLiveData.value = true
        auth.signInWithEmailAndPassword(email, password)
            .addOnCanceledListener { handleAuthCancelled() }
            .addOnFailureListener { error -> handleAuthFailure(error) }
            .addOnSuccessListener { result -> handleAuthSuccess(result) }
    }

    private fun handleAuthCancelled() {
        showProgressLiveData.value = false
    }

    private fun handleAuthFailure(error: Exception) {
        notifyErrorMessage(error.message)
        showProgressLiveData.value = false
    }

    private fun handleAuthSuccess(result: AuthResult) {
        showProgressLiveData.value = false
        loginSuccessLiveData.value = true
    }

    private fun notifyErrorMessage(message: String?) {
        errorMessageLiveData.value = message
        errorMessageLiveData.value = null
    }
}