package com.example.androidbarcode.helper.qrcode

import android.content.Context
import android.view.View
import androidx.navigation.findNavController
import com.example.androidbarcode.R
import com.example.androidbarcode.fragments.BarcodeReaderDirections
import com.example.androidbarcode.helper.getDateTime
import com.example.androidbarcode.model.BarcodeResultModel
import com.google.android.material.snackbar.Snackbar
import com.google.zxing.BarcodeFormat
import com.google.zxing.Result
import com.google.zxing.client.result.*
import com.king.zxing.CameraScan
import java.sql.Timestamp
import java.util.*

object QrCodeParse {
    fun qrCodeParse(
        result: Result,
        viewModel: BarcodeResultModel,
        view: View,
        context: Context,
        mCameraScan: CameraScan?
    ) {
        when (ResultParser.parseResult(result).type) {
            //**************************QR WIFI TYPE***********************************************
            ParsedResultType.WIFI -> {
                val parsedResult = ResultParser.parseResult(result) as WifiParsedResult
                viewModel.setQrType(parsedResult.type)
                viewModel.setWifiName(parsedResult.ssid.toString())
                viewModel.setWifiPass(parsedResult.password.toString())
                viewModel.setEncryption(parsedResult.networkEncryption.toString())
                val wifiText =
                    "${parsedResult.networkEncryption}\n${parsedResult.ssid}\n${parsedResult.password}"
                viewModel.setBarcode(wifiText)
                viewModel.setCountry("NO DATA")
                viewModel.setFormat(BarcodeFormat.QR_CODE)

                try {
                    view.findNavController()
                        .navigate(BarcodeReaderDirections.actionBarcodeReaderToBarcodeResultFragment())
                } catch (ex: Exception) {
                    ex.printStackTrace()
                }
            }
            //**************************QR TEXT TYPE***********************************************

            ParsedResultType.TEXT -> {
                val parseText = ResultParser.parseResult(result) as TextParsedResult
                viewModel.setBarcode(parseText.text)
                viewModel.setCountry("NO DATA")
                viewModel.setQrType(parseText.type)
                viewModel.setFormat(BarcodeFormat.QR_CODE)
                try {
                    view.findNavController()
                        .navigate(BarcodeReaderDirections.actionBarcodeReaderToBarcodeResultFragment())
                } catch (ex: Exception) {
                    ex.printStackTrace()
                }
            }
            //**************************QR URI TYPE***********************************************

            ParsedResultType.URI -> {
                val parsUri = ResultParser.parseResult(result) as URIParsedResult
                val uri = parsUri.uri.toString()
                viewModel.setBarcode(uri)
                viewModel.setQrType(parsUri.type)
                viewModel.setFormat(BarcodeFormat.QR_CODE)
                viewModel.setCountry("NO DATA")
                try {
                    view.findNavController()
                        .navigate(BarcodeReaderDirections.actionBarcodeReaderToBarcodeResultFragment())
                } catch (ex: Exception) {
                    ex.printStackTrace()
                }
            }

            //**************************QR GEO TYPE***********************************************
            ParsedResultType.GEO -> {
                val parseGeo = ResultParser.parseResult(result) as GeoParsedResult
                viewModel.setLatitude(parseGeo.latitude.toString())
                viewModel.setLongitude(parseGeo.longitude.toString())
                viewModel.setBarcode("Latitude: ${parseGeo.latitude}\nLongitude: ${parseGeo.longitude}")
                viewModel.setQrType(parseGeo.type)
                viewModel.setFormat(BarcodeFormat.QR_CODE)
                viewModel.setCountry("NO DATA")
                try {
                    view.findNavController()
                        .navigate(BarcodeReaderDirections.actionBarcodeReaderToBarcodeResultFragment())
                } catch (ex: Exception) {
                    ex.printStackTrace()
                }
            }

            ParsedResultType.TEL -> {
                val phoneParse = ResultParser.parseResult(result) as TelParsedResult
                viewModel.setBarcode(phoneParse.number)
                viewModel.setQrType(phoneParse.type)
                viewModel.setFormat(BarcodeFormat.QR_CODE)
                viewModel.setCountry("NO DATA")
                try {
                    view.findNavController()
                        .navigate(BarcodeReaderDirections.actionBarcodeReaderToBarcodeResultFragment())
                } catch (ex: Exception) {
                    ex.printStackTrace()
                }
            }
            ParsedResultType.CALENDAR -> {
                val calendarParse = ResultParser.parseResult(result) as CalendarParsedResult
                val title = calendarParse.summary.toString()
                val location = calendarParse.location.toString()
                val description = calendarParse.description.toString()
                val startDate = getDateTime(calendarParse.startTimestamp)
                val endDate = getDateTime(calendarParse.endTimestamp)
                viewModel.setEvent(title,location,description, calendarParse.startTimestamp.toString(), calendarParse.endTimestamp.toString())
                val barcodeText =
                    "Title:$title\nLocation:$location\nStart Date: $startDate\nEnd Date: $endDate\nDescription: $description"
                viewModel.setBarcode(barcodeText)
                viewModel.setQrType(calendarParse.type)
                viewModel.setFormat(BarcodeFormat.QR_CODE)
                viewModel.setCountry("NO DATA")
                try {
                    view.findNavController()
                        .navigate(BarcodeReaderDirections.actionBarcodeReaderToBarcodeResultFragment())
                } catch (ex: Exception) {
                    ex.printStackTrace()
                }
            }
            else -> {
                Snackbar.make(view, R.string.qr_error, Snackbar.LENGTH_SHORT)
                    .setBackgroundTint(context.getColor(R.color.red))
                    .show()
                mCameraScan?.startCamera()
            }
        }
    }
}