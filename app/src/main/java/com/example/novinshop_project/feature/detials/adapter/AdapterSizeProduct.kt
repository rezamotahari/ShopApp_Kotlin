package com.example.novinshop_project.feature.detials.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.novinshop_project.R
import com.example.novinshop_project.data.ProductSizeItem

class AdapterSizeProduct(val size: List<ProductSizeItem>) :
    RecyclerView.Adapter<AdapterSizeProduct.SizeViewHolder>() {

    var onCLickSizeItem: OnclickItemSizeId? = null
    var selectedItemPos = -1
    var lastItemSelectedPos = -1
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SizeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_size, parent, false)
        return SizeViewHolder(view)
    }

    override fun onBindViewHolder(holder: SizeViewHolder, position: Int) {

        val itemSize = size[position]
        if (position == selectedItemPos) holder.selectedBg() else holder.defualtBg()


        holder.txtSize.text = itemSize.sizeName

        holder.itemView.setOnClickListener {
            selectedItemPos =holder.adapterPosition
            lastItemSelectedPos = if(lastItemSelectedPos == -1) selectedItemPos else{
                notifyItemChanged(lastItemSelectedPos)
                selectedItemPos
            }
            notifyItemChanged(selectedItemPos)

            onCLickSizeItem?.onClickSizeId(itemSize.sizeId.toInt())

        }
    }

    override fun getItemCount(): Int = size.size
    fun setOnSizeId(onCLickSizeItem: OnclickItemSizeId) {
        this.onCLickSizeItem = onCLickSizeItem
    }

    class SizeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtSize = itemView.findViewById<TextView>(R.id.txt_size)

        fun defualtBg() {
            txtSize.background =
                ContextCompat.getDrawable(itemView.context, R.drawable.shape_text_size)
        }

        fun selectedBg() {
            txtSize.background =
                ContextCompat.getDrawable(itemView.context, R.drawable.shape_text_size_selected)
            txtSize.setTextColor(ContextCompat.getColor(itemView.context,R.color.white))
        }

    }

    interface OnclickItemSizeId {
        fun onClickSizeId(sizeId: Int)
    }


}