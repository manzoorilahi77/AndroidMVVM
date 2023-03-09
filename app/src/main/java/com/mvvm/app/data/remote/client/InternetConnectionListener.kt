package com.cleopatra.data.remote.client

interface InternetConnectionListener {
    fun onInternetConnection(isInternetAvailable: Boolean)
}