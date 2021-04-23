package com.skillbox.github.network

import okhttp3.Interceptor
import okhttp3.Response

class AuthHeaderInterceptor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        val modifiedRequest = originalRequest.newBuilder()
            .addHeader("Authorization", "token ${Auth.accessToken}")
            .build()

        return chain.proceed(modifiedRequest)
    }
}
