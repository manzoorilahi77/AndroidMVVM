package com.gatebot.app.data.remote.client

import com.gatebot.app.data.local.Singleton
import okhttp3.Interceptor
import okhttp3.Response



abstract class NetworkConnectionInterceptor: Interceptor {

    abstract fun isInternetAvailable(): Boolean

    abstract fun onInternetUnavailable()

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        if (!Singleton.isNetworkConnected) {
            onInternetUnavailable()
        }
        return chain.proceed(request)
    }
}