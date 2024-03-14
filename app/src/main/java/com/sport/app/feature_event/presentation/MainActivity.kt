package com.sport.app.feature_event.presentation

import android.app.AlertDialog
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.sport.app.utils.DialogUtils.Companion.createConnectivityDialog
import com.sport.app.utils.broadcastReceiver.ConnectivityCallback
import com.sport.app.utils.broadcastReceiver.NetworkChangeReceiver
import dagger.hilt.android.AndroidEntryPoint
import io.sanghun.compose.video.cache.VideoPlayerCacheManager

@AndroidEntryPoint
class MainActivity: ComponentActivity(), ConnectivityCallback {

    private val networkChangeReceiver = NetworkChangeReceiver(this)
    private var connectivityDialog: AlertDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        VideoPlayerCacheManager.initialize(this, 1024 * 1024 * 1024)    // 1GB

        setContent {
            MainComponent()
        }
    }

    override fun onStart() {
        super.onStart()
        val filter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        registerReceiver(networkChangeReceiver, filter)
    }

    override fun onStop() {
        super.onStop()
        unregisterReceiver(networkChangeReceiver)
    }

    override fun onConnectivityAvailable() {
        if (connectivityDialog?.isShowing == true) {
            connectivityDialog?.dismiss()
        }
    }

    override fun onConnectivityLost() {
        connectivityDialog = this.createConnectivityDialog()
        connectivityDialog?.show()
    }
}


