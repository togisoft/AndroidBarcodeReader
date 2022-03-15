package com.example.androidbarcode.helper

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.wifi.WifiManager
import android.view.View
import com.example.androidbarcode.R
import com.google.android.material.snackbar.Snackbar
import java.sql.Timestamp
import java.text.MessageFormat
import java.text.SimpleDateFormat
import java.util.*


//Send Text Share
fun sendText(context: Context, activity: Activity, text: String) {
    val textFinal = MessageFormat.format(context.getString(R.string.app_name))
    val newText = textFinal + "\n" + text
    val intent = Intent(Intent.ACTION_SEND)
        .setType("text/plain")
        .putExtra(Intent.EXTRA_SUBJECT, context.getString(R.string.app_name))
        .putExtra(Intent.EXTRA_TEXT, newText)
    if (activity.packageManager?.resolveActivity(intent, 0) != null) {
        context.startActivity(intent)
    }
}

//Locale Date
@SuppressLint("SimpleDateFormat")
fun getDate(): String {
    val date = Calendar.getInstance().time
    val formatter = SimpleDateFormat("EEEE, dd MM yyyy, hh:mm:ss a")
    return formatter.format(date)

}

fun getRandomName(length: Int): String {
    val charset = ('a'..'z') + ('A'..'Z') + ('0'..'9')

    return List(length) { charset.random() }
        .joinToString("")
}

fun openWifiSettings(context: Context) {
    context.startActivity(Intent(WifiManager.ACTION_PICK_WIFI_NETWORK))
}

fun snackBarSuccess(view: View, context: Context, text: Int) {
    Snackbar.make(view, text, Snackbar.LENGTH_SHORT)
        .setBackgroundTint(context.getColor(R.color.dark_green))
        .show()
}

fun snackBarError(view: View, context: Context, text: Int) {
    Snackbar.make(view, text, Snackbar.LENGTH_SHORT)
        .setBackgroundTint(context.getColor(R.color.red))
        .show()
}

@SuppressLint("SimpleDateFormat")
fun getDateTime(s:Long):String? {
    return try {
        val sdf =SimpleDateFormat("dd/MM/yyyy HH:mm")
        val netDate = Date(Timestamp(s).time)
        sdf.format(netDate)
    }catch (e:Exception) {
        e.toString()
    }
}