package com.baharlou.checkinternertsample

import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar

/**
 * CREATED BY MONA BAHARLOU */
open class MyBaseActivity : AppCompatActivity(), NetCheckerReceiver.NetCheckerReceiverListener {

    private var snackbar: Snackbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        registerReceiver(
            NetCheckerReceiver(),
            IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        )
    }


    private fun showMessage(isConnected: Boolean) {
        if (!isConnected) {

            val message = "Check the Internet Connection ! "

            snackbar = Snackbar.make(
                findViewById(R.id.topLayout),
                message,
                Snackbar.LENGTH_LONG
            )

            snackbar?.duration = Snackbar.LENGTH_INDEFINITE
            snackbar?.show()
        } else {
            //if has connection -> snackbar will disappear
            snackbar?.dismiss()
        }


    }

    /*
     * when connection changed, this will be called
     */
    override fun onNetworkConnectionChanged(isConnected: Boolean) {
        showMessage(isConnected)
    }

    override fun onResume() {
        super.onResume()
        NetCheckerReceiver.netConnectionCheckerReceiver = this
    }


}