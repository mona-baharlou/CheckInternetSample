package com.baharlou.checkinternertsample

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
/**
 * CREATED BY MONA BAHARLOU */
class NetCheckerReceiver : BroadcastReceiver() {

    interface NetCheckerReceiverListener {
        fun onNetworkConnectionChanged(isConnected: Boolean)
    }

    companion object {
        var netConnectionCheckerReceiver: NetCheckerReceiverListener? = null
    }

    override fun onReceive(context: Context, intent: Intent) {

        if (netConnectionCheckerReceiver != null) {
            netConnectionCheckerReceiver!!.onNetworkConnectionChanged(
                isConnectedOrConnecting(
                    context
                )
            )
        }
    }

    private fun isConnectedOrConnecting(context: Context): Boolean {
        val connMgr = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connMgr.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnectedOrConnecting
    }



}