package com.example.androidbarcode.helper

import android.app.Activity
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.widget.Toast
import com.example.androidbarcode.R
import com.example.androidbarcode.helper.BarcodeImageCreate.createBarCode
import com.google.zxing.BarcodeFormat
import com.king.zxing.util.CodeUtils


//Copy ClipBoard
fun copyClipboard(context: Context, activity: Activity, text: String) {
    val clipBoard: ClipboardManager =
        activity.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    val clipData: ClipData =
        ClipData.newPlainText(context.getString(R.string.app_name), text)
    clipBoard.setPrimaryClip(clipData)
    Toast.makeText(context, context.getString(R.string.copy), Toast.LENGTH_SHORT).show()
}

// Create QR CODE
fun createQrCode(text: String, context: Context): BitmapDrawable {
    val bitmap = CodeUtils.createQRCode(text, 600)
    return BitmapDrawable(context.resources, bitmap)
}


fun createBarcode(text: String, format: BarcodeFormat, context: Context): BitmapDrawable {
    val bitmap: Bitmap? = when (format) {
        BarcodeFormat.AZTEC -> createBarCode(text, format, 800, 200, null, false, 60, context.getColor(R.color.black))
        BarcodeFormat.PDF_417 -> createBarCode(text, format, 800, 200, null, false, 60, context.getColor(R.color.black))
        BarcodeFormat.ITF -> createBarCode(text, format, 800, 200, null, false, 60, context.getColor(R.color.black))
        BarcodeFormat.DATA_MATRIX -> createBarCode(text, format, 800, 200, null, false, 60, context.getColor(R.color.black))
        BarcodeFormat.RSS_14 -> createBarCode(text, format, 800, 200, null, false, 60, context.getColor(R.color.black))
        else -> createBarCode(text, format, 800, 200, null, true, 60, context.getColor(R.color.black))
    }

    Toast.makeText(context, context.getText(R.string.success), Toast.LENGTH_SHORT).show()
    return BitmapDrawable(context.resources, bitmap)
}


