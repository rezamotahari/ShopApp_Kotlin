package com.example.novinshop_project.feature.home.adapterHome

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.novinshop_project.R
import com.example.novinshop_project.data.ResponseBanners
import com.example.novinshop_project.services.ImageLoadingServices
import com.example.novinshop_project.view.MyImageView

class AdapterBanners(val banners:List<ResponseBanners>, val imageLoadingServices: ImageLoadingServices):RecyclerView.Adapter<AdapterBanners.BannerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerViewHolder {
     val view =LayoutInflater.from(parent.context).inflate(R.layout.item_banners,parent,false)
        return BannerViewHolder(view)
    }

    override fun onBindViewHolder(holder: BannerViewHolder, position: Int) {

        val itemBanners = banners[position]
        imageLoadingServices.loadImage(holder.imgBanners,itemBanners.image)
    }

    override fun getItemCount(): Int =banners.size
    class BannerViewHolder(itemView: View):RecyclerView.ViewHolder(itemView)
    {
        val imgBanners =itemView.findViewById<MyImageView>(R.id.img_banners_home)
    }
}