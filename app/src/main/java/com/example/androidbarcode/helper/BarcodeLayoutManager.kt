package com.example.androidbarcode.helper

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidbarcode.adaptor.CellClickListener
import com.example.androidbarcode.adaptor.ResultButtonAdapter
import com.example.androidbarcode.datamodel.ResultButton

fun layoutAdapter(
    recyclerView: RecyclerView,
    context: Context,
    qrSection: List<ResultButton>,
    cellClickListener: CellClickListener
) {
    recyclerView.layoutManager =
        LinearLayoutManager(context)
    recyclerView.adapter =
        ResultButtonAdapter(qrSection, context, cellClickListener)
}