package com.example.androidbarcode.adaptor.setposition

import android.app.Activity
import android.content.Context
import android.view.View
import android.widget.Toast
import com.example.androidbarcode.helper.*
import com.example.androidbarcode.model.BarcodeResultModel

fun wifiPosition(
    position: Int,
    context: Context,
    activity: Activity,
    viewModel: BarcodeResultModel,
    text: String,
    view: View
) {
    val wifiText =
        "WIFI:T:${viewModel.wifi_encryption.value};S:${viewModel.wifi_name.value};P:${viewModel.wifi_pass.value};;"
    when (position) {
        0 -> wifiConnect(
            viewModel.wifi_name.value.toString(),
            viewModel.wifi_pass.value.toString(),
            viewModel.wifi_encryption.value.toString(),
            context,
            view
        )
        1 -> openWifiSettings(context)
        2 -> copyClipboard(context, activity, viewModel.wifi_name.value.toString())
        3 -> copyClipboard(context, activity, viewModel.wifi_pass.value.toString())
        4 -> searchOnWeb(activity, text)
        5 -> sendText(
            context,
            activity,
            text
        )
        6 -> copyClipboard(
            context,
            activity,
            text
        )
        7 -> qrCodeImage(viewModel, wifiText, context, view)
        8 -> shareImageQr(context, wifiText)
        9 -> saveImageGallery(context, wifiText, view)
        10 -> printQrCode(viewModel, wifiText, context, activity)
        else -> Toast.makeText(context, "Non che", Toast.LENGTH_SHORT)
            .show()
    }
}
