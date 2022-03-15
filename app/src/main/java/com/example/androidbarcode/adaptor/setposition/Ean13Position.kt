package com.example.androidbarcode.adaptor.setposition

import android.app.Activity
import android.content.Context
import android.view.View

import com.example.androidbarcode.helper.*
import com.example.androidbarcode.model.BarcodeResultModel

    fun adapterPosition(
        position: Int,
        text: String,
        context: Context,
        activity: Activity,
        viewModel: BarcodeResultModel,
        view: View
    ) {
        when (position) {
            0 -> searchOnWeb(activity, text)
            1 -> searchOnAmazon(activity, text)
            2 -> searchOnEbay(activity, text)
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
            5 -> createBarcodeSection(viewModel, context, text, view)
            6 -> shareSectionImageBarcode(viewModel, context, text)
            7 -> saveSectionBarcodeImageGallery(context, viewModel, view, text)
            8 -> printSectionBarcode(viewModel, context, text, activity)
        }
    }

