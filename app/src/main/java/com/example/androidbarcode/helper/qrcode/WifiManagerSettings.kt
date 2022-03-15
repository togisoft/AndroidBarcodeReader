package com.example.androidbarcode.helper.qrcode

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.wifi.WifiManager
import android.net.wifi.WifiNetworkSuggestion
import android.os.Build
import android.view.View
import androidx.annotation.RequiresApi
import com.example.androidbarcode.R
import com.google.android.material.snackbar.Snackbar

@RequiresApi(Build.VERSION_CODES.Q)
fun wifiConnection(name: String, pass: String, context: Context,view:View) {
    val suggestions = WifiNetworkSuggestion.Builder()
        .setSsid(name)
        .setWpa2Passphrase(pass)
        .build()

    val suggestionList = listOf(suggestions)
    val wifiManager = context.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager

    val status = wifiManager.addNetworkSuggestions(suggestionList)
    if (status != WifiManager.STATUS_NETWORK_SUGGESTIONS_SUCCESS) {
        Snackbar.make(view, R.string.error, Snackbar.LENGTH_SHORT)
            .setBackgroundTint(context.getColor(R.color.red))
            .show()
    }
    val intentFilter = IntentFilter(WifiManager.ACTION_WIFI_NETWORK_SUGGESTION_POST_CONNECTION)
    val broadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            if (!intent?.action.equals(WifiManager.ACTION_WIFI_NETWORK_SUGGESTION_POST_CONNECTION)) {
                return
            }
            Snackbar.make(view, R.string.wait_wifi, Snackbar.LENGTH_SHORT)
                .setBackgroundTint(context?.getColor(R.color.orange)!!)
                .show()
            if (status == WifiManager.STATUS_NETWORK_SUGGESTIONS_SUCCESS){
                Snackbar.make(view, R.string.success, Snackbar.LENGTH_SHORT)
                    .setBackgroundTint(context.getColor(R.color.dark_green))
                    .show()
            }
        }
    }
    context.registerReceiver(broadcastReceiver, intentFilter)
}