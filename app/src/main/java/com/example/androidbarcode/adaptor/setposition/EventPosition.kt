package com.example.androidbarcode.adaptor.setposition

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.provider.CalendarContract
import android.view.View
import android.widget.Toast
import com.example.androidbarcode.helper.copyClipboard
import com.example.androidbarcode.helper.sendText
import com.example.androidbarcode.model.BarcodeResultModel

fun eventPositionQr(
    position: Int,
    context: Context,
    activity: Activity,
    viewModel: BarcodeResultModel,
    text: String,
    view: View
) {
    when (position) {
        0 -> addCalendarEvent(viewModel,activity)
        3 -> sendText(
            context,
            activity,
            text
        )
        4 -> copyClipboard(
            context,
            activity,
            text
        )
        5 -> qrCodeImage(viewModel, text, context, view)
        6 -> shareImageQr(context, text)
        7 -> saveImageGallery(context, text, view)
        8 -> printQrCode(viewModel, text, context, activity)
        else -> Toast.makeText(context, "Non che", Toast.LENGTH_SHORT)
            .show()
    }
}


private fun addCalendarEvent(viewModel: BarcodeResultModel,activity: Activity) {
    val intent = Intent(Intent.ACTION_INSERT)
        .setData(CalendarContract.Events.CONTENT_URI)
        .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, viewModel.startDate.value)
        .putExtra(CalendarContract.EXTRA_EVENT_END_TIME, viewModel.endDate.value)
        .putExtra(CalendarContract.Events.TITLE,viewModel.eventTitle.value)
        .putExtra(CalendarContract.Events.DESCRIPTION,viewModel.eventDesc.value)
        .putExtra(CalendarContract.Events.EVENT_LOCATION,viewModel.eventLocation.value)
        activity.startActivity(intent)
}