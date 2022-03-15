package com.example.androidbarcode.adaptor.setposition

import android.app.Activity
import android.content.Context
import android.view.View
import android.widget.Toast
import com.example.androidbarcode.helper.*
import com.example.androidbarcode.model.BarcodeResultModel

fun geoPosition(
    position: Int,
    context: Context,
    activity: Activity,
    viewModel: BarcodeResultModel,
    text: String,
    view: View
) {
    val latitude = viewModel.latitude.value.toString()
    val longitude = viewModel.longitude.value.toString()
    val geoText = "geo:$latitude,$longitude"
    when (position) {
        0 -> openGoogleMaps(activity, latitude, longitude)
        1 -> openYandexMaps(activity, latitude, longitude)
        2 -> openBingMaps(activity, latitude, longitude)
        3 -> copyClipboard(context, activity, text)
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
        7 -> qrCodeImage(viewModel, geoText, context, view)
        8 -> shareImageQr(context, geoText)
        9 -> saveImageGallery(context, geoText, view)
        10 -> printQrCode(viewModel, geoText, context, activity)
        else -> Toast.makeText(context, "Non che", Toast.LENGTH_SHORT)
            .show()
    }
}
