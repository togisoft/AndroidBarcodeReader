package com.example.androidbarcode.adaptor

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.androidbarcode.R
import com.example.androidbarcode.datamodel.ResultButton

class ResultButtonAdapter(
    private val resultButtonList: List<ResultButton>,
    private val context: Context,
    private val cellClickListener: CellClickListener,
) :
    RecyclerView.Adapter<ResultButtonAdapter.ResultButtonViewHolder>() {

    class ResultButtonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val icon: ImageView = itemView.findViewById(R.id.select_icon_image)
        val sectionText: TextView = itemView.findViewById(R.id.section_text)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultButtonViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.barcode_button_list, parent, false)
        return ResultButtonViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ResultButtonViewHolder, position: Int) {
        val currentItem = resultButtonList[position]
        holder.icon.setImageResource(currentItem.icon)
        holder.icon.backgroundTintList =
            ColorStateList.valueOf(Color.parseColor(currentItem.sectionColor))
        holder.sectionText.setText(currentItem.sectionName)
        holder.itemView.setOnClickListener {
            cellClickListener.onCellClickListener(holder.adapterPosition)
        }

    }

    override fun getItemCount() = resultButtonList.size
}