package com.example.novinshop_project.feature.home.subCatLevel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.novinshop_project.R
import com.example.novinshop_project.data.ResponseSubCatLevel
import com.example.novinshop_project.services.ImageLoadingServices
import com.example.novinshop_project.view.MyImageView

class AdapterSubcatLevel(val subCats:List<ResponseSubCatLevel>, val imageLoadingServices: ImageLoadingServices):RecyclerView.Adapter<AdapterSubcatLevel.BannerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerViewHolder {
     val view =LayoutInflater.from(parent.context).inflate(R.layout.item_subcat_level,parent,false)
        return BannerViewHolder(view)
    }

    override fun onBindViewHolder(holder: BannerViewHolder, position: Int) {

        val itemSubCat = subCats[position]
        imageLoadingServices.loadImage(holder.imgBanners,itemSubCat.image)
        holder.txtCount.text = itemSubCat.totalProducts
        holder.txtTitle.text = itemSubCat.title

    }

    override fun getItemCount(): Int =subCats.size
    class BannerViewHolder(itemView: View):RecyclerView.ViewHolder(itemView)
    {
        val imgBanners =itemView.findViewById<MyImageView>(R.id.img_sub_cat_level)
        val txtTitle =itemView.findViewById<TextView>(R.id.txt_tilte_subcat_level)
        val txtCount =itemView.findViewById<TextView>(R.id.txt_count_subcat_level)
    }
}