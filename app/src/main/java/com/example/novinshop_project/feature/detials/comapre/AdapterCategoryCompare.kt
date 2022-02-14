package com.example.novinshop_project.feature.detials.comapre

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.novinshop_project.R
import com.example.novinshop_project.data.ResponseCategoryCompare
import com.example.novinshop_project.services.ImageLoadingServices
import com.example.novinshop_project.view.MyImageView

class AdapterCategoryCompare(val categoryCompare:List<ResponseCategoryCompare>, val imageLoadingServices: ImageLoadingServices):RecyclerView.Adapter<AdapterCategoryCompare.CategoryCompareViewHolder>() {

    var onClickCategoryItem :OnClickCategoryItem?=null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryCompareViewHolder {
     val view =LayoutInflater.from(parent.context).inflate(R.layout.item_category_compare,parent,false)
        return CategoryCompareViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryCompareViewHolder, position: Int) {

        val itemCompare = categoryCompare[position]
        imageLoadingServices.loadImage(holder.imgCompare,itemCompare.image)
        holder.txtCompare.text = itemCompare.name
        holder.itemView.setOnClickListener {

            onClickCategoryItem?.onCLickCategory(itemCompare.id)
        }

    }

    override fun getItemCount(): Int =categoryCompare.size
    fun setItemClickCategory(onClickCategoryItem: OnClickCategoryItem)
    {
        this.onClickCategoryItem = onClickCategoryItem
    }

    class CategoryCompareViewHolder(itemView: View):RecyclerView.ViewHolder(itemView)
    {
        val imgCompare =itemView.findViewById<MyImageView>(R.id.img_category_compare)
        val txtCompare =itemView.findViewById<TextView>(R.id.txt_compare_category)
    }


    interface OnClickCategoryItem
    {
        fun onCLickCategory(productIdTwo :Int)
    }
}