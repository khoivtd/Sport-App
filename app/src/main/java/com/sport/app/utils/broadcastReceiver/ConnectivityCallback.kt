package com.sport.app.utils.broadcastReceiver

interface ConnectivityCallback {

    /**
     * Called when network connectivity becomes available.
     */
    fun onConnectivityAvailable()

    /**
     * Called when network connectivity is lost.
     */
    fun onConnectivityLost()
}