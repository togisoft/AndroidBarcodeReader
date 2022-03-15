package com.example.androidbarcode.helper

import android.content.Context
import android.net.wifi.WifiManager
import android.os.Build
import android.util.Log
import android.view.View
import com.example.androidbarcode.R
import com.example.androidbarcode.helper.qrcode.wifiConnection
import com.google.android.material.snackbar.Snackbar
import com.wifimanagerwrapper.WifiConnectivityCallbackResult
import com.wifimanagerwrapper.WifiManagerWrapper


fun wifiConnect(name: String, pass: String, typeEnc: String, context: Context, view: View) {

    val wifiManager: WifiManagerWrapper = WifiManagerWrapper()
    val wifiManagerWrapper = wifiManager.wifiManagerInti(context)
    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {

        if (wifiManagerWrapper.isWifiEnabled() == true) {
            if (!wifiManagerWrapper.isConnectedTo(name)) {
                if (typeEnc == "WPA") {
                    wifiManagerWrapper.connectWifi(name, pass,
                        wifiManagerWrapper.WPA_WPA2_PSK,
                        object : WifiConnectivityCallbackResult {
                            override fun wifiConnectionStatusChangedResult() {
                                Snackbar.make(view, R.string.success, Snackbar.LENGTH_SHORT)
                                    .setBackgroundTint(context.getColor(R.color.dark_green))
                                    .show()
                            }
                        })
                } else {
                    wifiManagerWrapper.connectWifi(name, pass,
                        wifiManagerWrapper.WEP,
                        object : WifiConnectivityCallbackResult {
                            override fun wifiConnectionStatusChangedResult() {

                                Snackbar.make(view, R.string.success, Snackbar.LENGTH_SHORT)
                                    .setBackgroundTint(context.getColor(R.color.dark_green))
                                    .show()

                            }
                        })
                }
            } else {
                Snackbar.make(view, R.string.wifi_is_connected, Snackbar.LENGTH_SHORT)
                    .setBackgroundTint(context.getColor(R.color.red))
                    .show()
            }
        } else {
            Snackbar.make(view, R.string.please_open_wifi, Snackbar.LENGTH_SHORT)
                .setBackgroundTint(context.getColor(R.color.red))
                .show()
        }
    }else {
        wifiConnection(name, pass, context,view)
    }
}
