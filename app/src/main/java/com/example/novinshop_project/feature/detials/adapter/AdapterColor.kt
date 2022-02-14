package com.example.novinshop_project.feature.detials.adapter

import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.compose.ui.graphics.Color
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.novinshop_project.R
import com.example.novinshop_project.data.ProductColorsItem
import com.google.android.material.card.MaterialCardView

class AdapterColor(val colors: List<ProductColorsItem>) :
    RecyclerView.Adapter<AdapterColor.ColorViewHolder>() {
    var selectedItemPos = -1
    var lastItemSelectedPos = -1
    var onclickColorId: OnClickItemColor? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColorViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_color_product, parent, false)
        return ColorViewHolder(view)
    }

    override fun onBindViewHolder(holder: ColorViewHolder, position: Int) {

        val itemColor = colors[position]
        if (position == selectedItemPos) holder.selectedBg() else holder.defualtBg()
        holder.txtColor.text = itemColor.colorName

        holder.cardView.setCardBackgroundColor(android.graphics.Color.parseColor(itemColor.colorCode))

        holder.itemView.setOnClickListener {
            selectedItemPos =holder.adapterPosition
            lastItemSelectedPos = if(lastItemSelectedPos == -1) selectedItemPos else{
                notifyItemChanged(lastItemSelectedPos)
                selectedItemPos
            }
            notifyItemChanged(selectedItemPos)
            onclickColorId!!.onClickColorId(itemColor.colorId.toInt())

        }
    }

    override fun getItemCount(): Int = colors.size

    fun setOnClickColorId(onclickColorId: OnClickItemColor) {
        this.onclickColorId = onclickColorId
    }

    class ColorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cardView = itemView.findViewById<MaterialCardView>(R.id.mc)
        val txtColor = itemView.findViewById<TextView>(R.id.txt_color)
        val imgCheck = itemView.findViewById<ImageView>(R.id.img_Check)
        val rltvColor = itemView.findViewById<RelativeLayout>(R.id.rltv_color)

        fun defualtBg() {
       imgCheck.setBackground(null)
            rltvColor.background =
                ContextCompat.getDrawable(itemView.context, R.drawable.shape_rtlv_color)

        }

        fun selectedBg() {
            imgCheck.background =
                ContextCompat.getDrawable(itemView.context, R.drawable.ic_baseline_check_24)
            rltvColor.background =
                ContextCompat.getDrawable(itemView.context, R.drawable.shape_rtlv_color_selected)
        }

    }

    interface OnClickItemColor {
        fun onClickColorId(colorId: Int)
    }
}