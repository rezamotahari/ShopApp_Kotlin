package com.example.novinshop_project.feature.category.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.novinshop_project.R
import com.example.novinshop_project.data.ResponseSubCat
import com.example.novinshop_project.services.ImageLoadingServices
import com.example.novinshop_project.view.MyImageView

class AdapterSubCat(val subCat:List<ResponseSubCat>, val imageLoadingServices: ImageLoadingServices):RecyclerView.Adapter<AdapterSubCat.BannerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerViewHolder {
     val view =LayoutInflater.from(parent.context).inflate(R.layout.item_categoryl,parent,false)
        return BannerViewHolder(view)
    }

    override fun onBindViewHolder(holder: BannerViewHolder, position: Int) {

        val itemSubCat = subCat[position]
        imageLoadingServices.loadImage(holder.imgSubCat,itemSubCat.image)
        holder.txtTitle.text = itemSubCat.title
    }

    override fun getItemCount(): Int =subCat.size
    class BannerViewHolder(itemView: View):RecyclerView.ViewHolder(itemView)
    {
        val imgSubCat =itemView.findViewById<MyImageView>(R.id.img_sub_cat_level)
        val txtTitle =itemView.findViewById<TextView>(R.id.txt_tilte_subcat_level)
    }
}