package com.example.androidbarcode.adaptor.setposition

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.ContactsContract
import android.view.View
import android.widget.Toast
import com.example.androidbarcode.helper.copyClipboard
import com.example.androidbarcode.helper.sendText
import com.example.androidbarcode.model.BarcodeResultModel

fun telPositionQr(
    position: Int,
    context: Context,
    activity: Activity,
    viewModel: BarcodeResultModel,
    text: String,
    view: View
) {
    when (position) {
        0 -> call(viewModel.barcode.value.toString(), activity)
        1 -> addContact(viewModel.barcode.value.toString(), activity)
        2 -> sendSms(viewModel.barcode.value.toString(),activity)
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

private fun call(text: String, activity: Activity) {
    val intent = Intent(Intent.ACTION_DIAL)
    intent.data = Uri.parse("tel:$text")
    activity.startActivity(intent)
}

private fun addContact(phone: String, activity: Activity) {
    val intent = Intent(ContactsContract.Intents.Insert.ACTION)
    intent.type = ContactsContract.RawContacts.CONTENT_TYPE
    intent.putExtra(ContactsContract.Intents.Insert.PHONE, phone)
    activity.startActivity(intent)
}

private fun sendSms(phone: String,activity: Activity) {
    val uri = Uri.parse("smsto:$phone")
    val intent = Intent(Intent.ACTION_SENDTO,uri)
    activity.startActivity(intent)
}