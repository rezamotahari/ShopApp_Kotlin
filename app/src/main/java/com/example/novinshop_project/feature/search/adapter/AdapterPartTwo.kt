package com.example.novinshop_project.feature.search.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.novinshop_project.R
import com.example.novinshop_project.data.Part2Item
import com.example.novinshop_project.feature.home.adapterHome.AdapterAmazing
import com.example.novinshop_project.feature.search.Search_Activity
import com.example.novinshop_project.services.ImageLoadingServices
import com.example.novinshop_project.view.MyImageView

class AdapterPartTwo(val partTwo: List<Part2Item>, val imageLoadingServices: ImageLoadingServices) :
    RecyclerView.Adapter<AdapterPartTwo.CateoryViewHolder>() {

    lateinit var onClickProductItem:OnClickProductItem

    fun setOnCLickProductItem(onClickProductItem: Search_Activity) {
        this.onClickProductItem = onClickProductItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CateoryViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_part_two, parent, false)

        return CateoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CateoryViewHolder, position: Int) {
        val itemPartTwo = partTwo[position]
        imageLoadingServices.loadImage(holder.imgPartTwo, itemPartTwo.image)
        holder.txtParttwo.text = itemPartTwo.name
        holder.itemView.setOnClickListener {

            onClickProductItem.onClickProduct(itemPartTwo.id.toInt())
        }

    }

    override fun getItemCount(): Int = partTwo.size

    class CateoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgPartTwo = itemView.findViewById<MyImageView>(R.id._img_search)
        val txtParttwo = itemView.findViewById<TextView>(R.id.txt_search)
    }

    interface OnClickProductItem {
        fun onClickProduct(productId: Int)

    }
}