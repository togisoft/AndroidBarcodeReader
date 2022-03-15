package com.example.androidbarcode.helper

import android.app.Activity
import android.content.Intent
import android.net.Uri

fun searchOnWeb(activity: Activity, text: String) {
    val url = "https://www.google.com/search?q=${text}"
    val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
    activity.startActivity(browserIntent)
}

fun searchOnAmazon(activity: Activity, text: String) {
    val url =
        "http://www.amazon.com/s/ref=nb_sb_noss_2?url=search-alias%3Daps&field-keywords=${text}"
    val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
    activity.startActivity(browserIntent)
}

fun searchOnEbay(activity: Activity, text: String) {
    val url = "https://www.ebay.com/sch/i.html?_from=R40&_trksid=p2380057.m570.l1313&_nkw=${text}"
    val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
    activity.startActivity(browserIntent)
}

fun openLink(activity: Activity, text: String) {
    val url = text
    val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
    activity.startActivity(browserIntent)
}

fun openGoogleMaps(activity: Activity, latitude: String, longitude: String) {
    val url = "https://www.google.com/maps/@$latitude,$longitude,14z"
    val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
    activity.startActivity(browserIntent)
}

fun openYandexMaps(activity: Activity, latitude: String, longitude: String) {
    val url = "https://yandex.ru/maps/?pt=$latitude,$longitude&z=18&l=map"
    val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
    activity.startActivity(browserIntent)
}

fun openBingMaps(activity: Activity, latitude: String, longitude: String) {
    val url = "https://bing.com/maps/default.aspx?cp=$latitude~-$longitude"
    val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
    activity.startActivity(browserIntent)
}