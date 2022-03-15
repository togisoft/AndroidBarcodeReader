package com.example.androidbarcode.model

import android.graphics.drawable.BitmapDrawable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.zxing.BarcodeFormat
import com.google.zxing.client.result.ParsedResultType

class BarcodeResultModel : ViewModel() {

    private val _barcode = MutableLiveData<String>()
    val barcode: LiveData<String> = _barcode

    private val _barcodeFormat = MutableLiveData<BarcodeFormat>()
    val barcodeFormat: LiveData<BarcodeFormat> = _barcodeFormat

    private val _barcodeCountry = MutableLiveData<String>()
    val barcodeCountry: LiveData<String> = _barcodeCountry

    private val _barcodeImage = MutableLiveData<BitmapDrawable>()
    val barcodeImage: LiveData<BitmapDrawable> = _barcodeImage

    private val _wifi_encryption = MutableLiveData<String>()
    val wifi_encryption: LiveData<String> = _wifi_encryption

    private val _qr_type = MutableLiveData<ParsedResultType>()
    val qr_type: LiveData<ParsedResultType> = _qr_type

    private val _wifi_name = MutableLiveData<String>()
    val wifi_name: LiveData<String> = _wifi_name

    private val _wifiPass = MutableLiveData<String>()
    val wifi_pass: LiveData<String> = _wifiPass

    private val _latitude = MutableLiveData<String>()
    val latitude: LiveData<String> = _latitude

    private val _longitude = MutableLiveData<String>()
    val longitude: LiveData<String> = _longitude

    private val _eventTitle = MutableLiveData<String>()
    val eventTitle: LiveData<String> = _eventTitle

    private val _eventLocation = MutableLiveData<String>()
    val eventLocation: LiveData<String> = _eventLocation

    private val _eventDesc = MutableLiveData<String>()
    val eventDesc: LiveData<String> = _eventDesc

    private val _startDate = MutableLiveData<String>()
    val startDate: LiveData<String> = _startDate

    private val _endDate = MutableLiveData<String>()
    val endDate: LiveData<String> = _endDate


    fun setEvent(
        title: String,
        location: String,
        desc: String,
        startDate: String,
        endDate: String
    ) {
        _eventTitle.value = title
        _eventLocation.value = location
        _eventDesc.value = desc
        _startDate.value = startDate
        _endDate.value = endDate
    }


    fun setLatitude(latitude: String) {
        _latitude.value = latitude
    }

    fun setLongitude(longitude: String) {
        _longitude.value = longitude
    }

    fun setEncryption(encryption: String) {
        _wifi_encryption.value = encryption
    }

    fun setWifiName(name: String) {
        _wifi_name.value = name
    }

    fun setWifiPass(pass: String) {
        _wifiPass.value = pass
    }

    fun setQrType(type: ParsedResultType) {
        _qr_type.value = type
    }

    fun setBarcodeImage(image: BitmapDrawable) {
        _barcodeImage.value = image
    }

    fun setBarcode(newBarcode: String) {
        _barcode.value = newBarcode
    }

    fun setFormat(format: BarcodeFormat) {
        _barcodeFormat.value = format
    }

    fun setCountry(country: String) {
        _barcodeCountry.value = country
    }

}