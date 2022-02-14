package com.example.novinshop_project.feature.category.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.novinshop_project.R
import com.example.novinshop_project.data.SubcatItem
import com.example.novinshop_project.services.ImageLoadingServices
import com.example.novinshop_project.view.MyImageView

class AdapterCategory(val category:List<SubcatItem>, val imageLoadingServices: ImageLoadingServices):RecyclerView.Adapter<AdapterCategory.BannerViewHolder>() {

    var onClickSubCatItem : OnClickSubCatItem?=null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerViewHolder {
     val view =LayoutInflater.from(parent.context).inflate(R.layout.item_categoryl,parent,false)
        return BannerViewHolder(view)
    }

    override fun onBindViewHolder(holder: BannerViewHolder, position: Int) {

        val itemCategory = category[position]
        imageLoadingServices.loadImage(holder.imgBanners,itemCategory.subImage)
//        holder.txtCount.text = itemCategory.
        holder.txtTitle.text = itemCategory.subTitle
        holder.itemView.setOnClickListener {
            onClickSubCatItem?.onClickSubCat(itemCategory.subId.toInt())

        }

    }

    override fun getItemCount(): Int = category.size
    fun setSubCatId(onClickSubCatItem : OnClickSubCatItem)
    {
        this.onClickSubCatItem= onClickSubCatItem
    }
    class BannerViewHolder(itemView: View):RecyclerView.ViewHolder(itemView)
    {
        val imgBanners =itemView.findViewById<MyImageView>(R.id.img_sub_cat_level)
        val txtTitle =itemView.findViewById<TextView>(R.id.txt_tilte_subcat_level)
        val txtCount =itemView.findViewById<TextView>(R.id.txt_count_subcat_level)
    }
    interface OnClickSubCatItem
    {
        fun onClickSubCat(catId:Int)
    }
}