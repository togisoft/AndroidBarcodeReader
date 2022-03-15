package com.example.androidbarcode.helper

import com.example.androidbarcode.R
import com.example.androidbarcode.datamodel.ResultButton

//QR CODE SECTION
fun qrSection(size: Int): List<ResultButton> {
    val list = ArrayList<ResultButton>()
    for (i in 0 until size) {
        val icon = when (i % 7) {
            0 -> R.drawable.search
            1 -> R.drawable.share
            2 -> R.drawable.copy
            3 -> R.drawable.ic_qr_code
            4 -> R.drawable.share
            5 -> R.drawable.save
            6 -> R.drawable.print
            else -> R.drawable.print
        }
        val sectionText = when (i % 7) {
            0 -> R.string.search_web
            1 -> R.string.share_text
            2 -> R.string.copy_clipboard
            3 -> R.string.create_barcode
            4 -> R.string.share_image
            5 -> R.string.save_image
            6 -> R.string.print
            else -> R.string.print
        }
        val sectionColor = when (i % 7) {
            0 -> "#FF9100"
            1 -> "#FF9100"
            2 -> "#FF9100"
            3 -> "#00C853"
            4 -> "#00C853"
            5 -> "#00C853"
            6 -> "#00C853"
            else -> "#00C853"
        }

        val item = ResultButton(
            icon,
            sectionText, sectionColor
        )
        list += item
    }
    return list
}

fun qrLinkSection(size: Int): List<ResultButton> {
    val list = ArrayList<ResultButton>()
    for (i in 0 until size) {
        val icon = when (i % 7) {
            0 -> R.drawable.open_link
            1 -> R.drawable.share
            2 -> R.drawable.copy
            3 -> R.drawable.ic_qr_code
            4 -> R.drawable.share
            5 -> R.drawable.save
            6 -> R.drawable.print
            else -> R.drawable.print
        }
        val sectionText = when (i % 7) {
            0 -> R.string.open_link
            1 -> R.string.share_text
            2 -> R.string.copy_clipboard
            3 -> R.string.create_barcode
            4 -> R.string.share_image
            5 -> R.string.save_image
            6 -> R.string.print
            else -> R.string.print
        }
        val sectionColor = when (i % 7) {
            0 -> "#FF9100"
            1 -> "#FF9100"
            2 -> "#FF9100"
            3 -> "#00C853"
            4 -> "#00C853"
            5 -> "#00C853"
            6 -> "#00C853"
            else -> "#00C853"
        }

        val item = ResultButton(
            icon,
            sectionText, sectionColor
        )
        list += item
    }
    return list
}

fun qrWifiSection(size: Int): List<ResultButton> {
    val list = ArrayList<ResultButton>()
    for (i in 0 until size) {
        val icon = when (i % 11) {
            0 -> R.drawable.ic_wifi
            1 -> R.drawable.open_settings
            2 -> R.drawable.copy
            3 -> R.drawable.copy
            4 -> R.drawable.search
            5 -> R.drawable.share
            6 -> R.drawable.copy
            7 -> R.drawable.ic_qr_code
            8 -> R.drawable.share
            9 -> R.drawable.save
            10 -> R.drawable.print
            else -> R.drawable.print
        }
        val sectionText = when (i % 11) {
            0 -> R.string.connect_wifi
            1 -> R.string.open_setting
            2 -> R.string.copy_network_name
            3 -> R.string.copy_network_password
            4 -> R.string.search_web
            5 -> R.string.share_text
            6 -> R.string.copy_clipboard
            7 -> R.string.create_barcode
            8 -> R.string.share_image
            9 -> R.string.save_image
            10 -> R.string.print
            else -> R.string.print
        }
        val sectionColor = when (i % 11) {
            0 -> "#FF9100"
            1 -> "#FF9100"
            2 -> "#FF9100"
            3 -> "#FF9100"
            4 -> "#FF018786"
            5 -> "#FF018786"
            6 -> "#FF018786"
            7 -> "#FF018786"
            8 -> "#00C853"
            9 -> "#00C853"
            10 -> "#00C853"
            else -> "#00C853"
        }

        val item = ResultButton(
            icon,
            sectionText, sectionColor
        )
        list += item
    }
    return list
}

fun barcodeSection(size: Int): List<ResultButton> {
    val list = ArrayList<ResultButton>()
    for (i in 1 until size) {
        val icon = when (i % 9) {
            1 -> R.drawable.search
            2 -> R.drawable.ic_amazon
            3 -> R.drawable.ic_ebay
            4 -> R.drawable.share
            5 -> R.drawable.copy
            6 -> R.drawable.ic_barcode
            7 -> R.drawable.share
            8 -> R.drawable.save
            else -> R.drawable.print
        }
        val sectionText = when (i % 9) {
            1 -> R.string.search_web
            2 -> R.string.search_amazon
            3 -> R.string.search_ebay
            4 -> R.string.share_text
            5 -> R.string.copy_clipboard
            6 -> R.string.create_barcode
            7 -> R.string.share_image
            8 -> R.string.save_image
            else -> R.string.print
        }
        val sectionColor = when (i % 9) {
            1 -> "#FF9100"
            2 -> "#FF9100"
            3 -> "#FF9100"
            4 -> "#00C853"
            5 -> "#00C853"
            6 -> "#00C853"
            7 -> "#FF018786"
            8 -> "#FF018786"
            else -> "#FF018786"
        }

        val item = ResultButton(
            icon,
            sectionText, sectionColor
        )
        list += item
    }
    return list
}

fun qrGeoSection(size: Int): List<ResultButton> {
    val list = ArrayList<ResultButton>()
    for (i in 0 until size) {
        val icon = when (i % 11) {
            0 -> R.drawable.ic_google_maps
            1 -> R.drawable.ic_yandex
            2 -> R.drawable.ic_bing_svgrepo_com
            3 -> R.drawable.copy
            4 -> R.drawable.search
            5 -> R.drawable.share
            6 -> R.drawable.copy
            7 -> R.drawable.ic_qr_code
            8 -> R.drawable.share
            9 -> R.drawable.save
            10 -> R.drawable.print
            else -> R.drawable.print
        }
        val sectionText = when (i % 11) {
            0 -> R.string.open_google_map
            1 -> R.string.open_yandex_map
            2 -> R.string.open_bing_map
            3 -> R.string.copy_location_text
            4 -> R.string.search_web
            5 -> R.string.share_text
            6 -> R.string.copy_clipboard
            7 -> R.string.create_barcode
            8 -> R.string.share_image
            9 -> R.string.save_image
            10 -> R.string.print
            else -> R.string.print
        }
        val sectionColor = when (i % 11) {
            0 -> "#FF9100"
            1 -> "#FF9100"
            2 -> "#FF9100"
            3 -> "#FF9100"
            4 -> "#FF018786"
            5 -> "#FF018786"
            6 -> "#FF018786"
            7 -> "#FF018786"
            8 -> "#00C853"
            9 -> "#00C853"
            10 -> "#00C853"
            else -> "#00C853"
        }

        val item = ResultButton(
            icon,
            sectionText, sectionColor
        )
        list += item
    }
    return list
}

fun qrTelSection(size: Int): List<ResultButton> {
    val list = ArrayList<ResultButton>()
    for (i in 0 until size) {
        val icon = when (i % 11) {
            0 -> R.drawable.ic_phone
            1 -> R.drawable.ic_add_contact
            2 -> R.drawable.ic_sms
            3 -> R.drawable.share
            4 -> R.drawable.copy
            5 -> R.drawable.ic_qr_code
            6 -> R.drawable.share
            7 -> R.drawable.save
            8 -> R.drawable.print
            else -> R.drawable.print
        }
        val sectionText = when (i % 11) {
            0 -> R.string.call_phone
            1 -> R.string.add_contact
            2 -> R.string.send_sms
            3 -> R.string.share_text
            4 -> R.string.copy_clipboard
            5 -> R.string.create_barcode
            6 -> R.string.share_image
            7 -> R.string.save_image
            8 -> R.string.print
            else -> R.string.print
        }
        val sectionColor = when (i % 11) {
            0 -> "#FF9100"
            1 -> "#FF9100"
            2 -> "#FF9100"
            3 -> "#FF018786"
            4 -> "#FF018786"
            5 -> "#FF018786"
            else -> "#00C853"
        }

        val item = ResultButton(
            icon,
            sectionText, sectionColor
        )
        list += item
    }
    return list
}


fun qrEventSection(size: Int): List<ResultButton> {
    val list = ArrayList<ResultButton>()
    for (i in 0 until size) {
        val icon = when (i % 11) {
            0 -> R.drawable.ic_event
            1 -> R.drawable.share
            2 -> R.drawable.copy
            3 -> R.drawable.ic_qr_code
            4 -> R.drawable.share
            5 -> R.drawable.save
            6 -> R.drawable.print
            else -> R.drawable.print
        }
        val sectionText = when (i % 11) {
            0 -> R.string.add_event
            1 -> R.string.share_text
            2 -> R.string.copy_clipboard
            3 -> R.string.create_barcode
            4 -> R.string.share_image
            5 -> R.string.save_image
            6 -> R.string.print
            else -> R.string.print
        }
        val sectionColor = when (i % 11) {
            0 -> "#FF9100"
            1 -> "#FF9100"
            2 -> "#FF018786"
            3 -> "#FF018786"
            else -> "#00C853"
        }

        val item = ResultButton(
            icon,
            sectionText, sectionColor
        )
        list += item
    }
    return list
}