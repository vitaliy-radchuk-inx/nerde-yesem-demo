package com.demo.nerdeyesem.data.api

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(private val userKey: String) : Interceptor {
    companion object {
        private const val USER_KEY_HEADER_NAME = "user-key"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val authRequest = chain.request().newBuilder()
            .header(USER_KEY_HEADER_NAME, userKey)
            .build()

        return chain.proceed(authRequest)
    }
}