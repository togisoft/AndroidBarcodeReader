package com.example.androidbarcode.helper

import android.content.Context
import android.os.Build
import com.example.androidbarcode.R
import com.google.zxing.BarcodeFormat
import java.util.*


fun BarcodeFormat.toStringId(): Int {
    return when (this) {
        BarcodeFormat.AZTEC -> R.string.barcode_format_aztec
        BarcodeFormat.CODABAR -> R.string.barcode_format_codabar
        BarcodeFormat.CODE_39 -> R.string.barcode_format_code_39
        BarcodeFormat.CODE_93 -> R.string.barcode_format_code_93
        BarcodeFormat.CODE_128 -> R.string.barcode_format_code_128
        BarcodeFormat.DATA_MATRIX -> R.string.barcode_format_data_matrix
        BarcodeFormat.EAN_8 -> R.string.barcode_format_ean_8
        BarcodeFormat.EAN_13 -> R.string.barcode_format_ean_13
        BarcodeFormat.ITF -> R.string.barcode_format_itf_14
        BarcodeFormat.PDF_417 -> R.string.barcode_format_pdf_417
        BarcodeFormat.QR_CODE -> R.string.barcode_format_qr_code
        BarcodeFormat.UPC_A -> R.string.barcode_format_upc_a
        BarcodeFormat.UPC_E -> R.string.barcode_format_upc_e
        else -> R.string.barcode_format_qr_code
    }
}


fun getCurrentLocale(context: Context): Locale? {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        context.resources.configuration.locales[0]
    } else {
        @Suppress("deprecation")
        context.resources.configuration.locale
    }
}

fun BarcodeFormat.toImageId(): Int {
    return when (this) {
        BarcodeFormat.QR_CODE -> R.drawable.ic_qr_code
        BarcodeFormat.DATA_MATRIX -> R.drawable.ic_data_matrix
        BarcodeFormat.AZTEC -> R.drawable.ic_aztec
        BarcodeFormat.PDF_417 -> R.drawable.ic_pdf417
        else -> R.drawable.ic_barcode
    }
}

fun barcodeColor(format:Int): Int {
    return when (format) {
        R.string.barcode_format_qr_code -> R.color.blue
        R.string.barcode_format_data_matrix -> R.color.orange
        R.string.barcode_format_aztec -> R.color.orange
        R.string.barcode_format_pdf_417 -> R.color.orange
        R.string.barcode_format_maxi_code -> R.color.orange
        else -> R.color.green
    }
}

//Flag
fun String.toCountryEmoji(): String {
    if (this.length != 2) {
        return ""
    }

    val countryCodeCaps = uppercase(Locale.US)
    val firstLetter = Character.codePointAt(countryCodeCaps, 0) - 0x41 + 0x1F1E6
    val secondLetter = Character.codePointAt(countryCodeCaps, 1) - 0x41 + 0x1F1E6

    if (!countryCodeCaps[0].isLetter() || !countryCodeCaps[1].isLetter()) {
        return this
    }

    return String(Character.toChars(firstLetter)) + String(Character.toChars(secondLetter))
}

//Country Name and Flag
fun fullCountryName(country: String, context: Context): String {
    val currentLocale = getCurrentLocale(context) ?: return ""
    val countryName = Locale("", country).getDisplayName(currentLocale)
    val countryEmoji = country.toCountryEmoji()
    return "$countryEmoji $countryName"
}
