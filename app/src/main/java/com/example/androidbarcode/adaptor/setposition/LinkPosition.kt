package com.example.androidbarcode.adaptor.setposition

import android.app.Activity
import android.content.Context
import android.view.View
import android.widget.Toast
import com.example.androidbarcode.helper.copyClipboard
import com.example.androidbarcode.helper.openLink
import com.example.androidbarcode.helper.sendText
import com.example.androidbarcode.model.BarcodeResultModel

fun linkPositionQr(
    position: Int,
    context: Context,
    activity: Activity,
    viewModel: BarcodeResultModel,
    text: String,
    view: View
) {
    when (position) {
        0 -> openLink(activity, text)
        1 -> sendText(
            context,
            activity,
            text
        )
        2 -> copyClipboard(
            context,
            activity,
            text
        )
        3 -> qrCodeImage(viewModel, text, context, view)
        4 -> shareImageQr(context, text)
        5 -> saveImageGallery(context, text, view)
        6 -> printQrCode(viewModel, text, context, activity)
        else -> Toast.makeText(context, "Non che", Toast.LENGTH_SHORT)
            .show()
    }
}
