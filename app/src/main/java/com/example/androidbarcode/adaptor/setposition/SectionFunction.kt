package com.example.androidbarcode.adaptor.setposition

import android.app.Activity
import android.content.Context
import android.view.View
import androidx.navigation.findNavController
import com.example.androidbarcode.fragments.BarcodeResultFragmentDirections
import com.example.androidbarcode.helper.ImageSaveOptions
import com.example.androidbarcode.helper.createBarcode
import com.example.androidbarcode.helper.createQrCode
import com.example.androidbarcode.model.BarcodeResultModel


fun createBarcodeSection(
    viewModel: BarcodeResultModel,
    context: Context,
    text: String,
    view: View
) {
    viewModel.barcodeFormat.value?.let {
        com.example.androidbarcode.helper.createBarcode(
            text,
            it, context
        )
    }?.let { viewModel.setBarcodeImage(it) }

    view.findNavController()
        .navigate(BarcodeResultFragmentDirections.actionBarcodeResultFragmentToShowBarcodeImage())

}

fun shareSectionImageBarcode(viewModel: BarcodeResultModel, context: Context, text: String) {
    ImageSaveOptions.saveImageCache(
        context,
        com.example.androidbarcode.helper.createBarcode(
            text,
            viewModel.barcodeFormat.value!!, context
        )
    )
    ImageSaveOptions.share(context)
}

fun saveSectionBarcodeImageGallery(
    context: Context,
    viewModel: BarcodeResultModel,
    view: View,
    text: String
) {
    ImageSaveOptions.saveImagePngGallery(
        context, view, com.example.androidbarcode.helper.createBarcode(
            text,
            viewModel.barcodeFormat.value!!, context
        )
    )
}

fun printSectionBarcode(
    viewModel: BarcodeResultModel,
    context: Context,
    text: String,
    activity: Activity
) {
    viewModel.barcodeFormat.value?.let {
        createBarcode(
            text,
            it, context
        )
    }?.let { viewModel.setBarcodeImage(it) }
    ImageSaveOptions.printImage(activity, viewModel.barcodeImage.value!!)
}

//QR CODE IMAGE
fun qrCodeImage(viewModel: BarcodeResultModel, text: String, context: Context, view: View) {
    viewModel.setBarcodeImage(
        createQrCode(
            text,
            context
        )
    )
    view.findNavController()
        .navigate(BarcodeResultFragmentDirections.actionBarcodeResultFragmentToShowBarcodeImage())
}
//end

//QR CODE PRINT
fun printQrCode(viewModel: BarcodeResultModel, text: String, context: Context, activity: Activity) {
    viewModel.setBarcodeImage(
        createQrCode(
            text,
            context
        )
    )
    ImageSaveOptions.printImage(activity, viewModel.barcodeImage.value!!)

}

fun shareImageQr(context: Context, text: String) {
    ImageSaveOptions.saveImageCache(
        context,
        createQrCode(text, context)
    )
    ImageSaveOptions.share(context)
}


fun saveImageGallery(context: Context, text: String, view: View) {
    ImageSaveOptions.saveImagePngGallery(
        context,
        view,
        createQrCode(text, context)
    )
}