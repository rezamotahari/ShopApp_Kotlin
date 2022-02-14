package com.example.novinshop_project.feature.category.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.novinshop_project.R
import com.example.novinshop_project.data.ResponseBrands
import com.example.novinshop_project.services.ImageLoadingServices
import com.example.novinshop_project.view.MyImageView

class AdapterBrands(val brands:List<ResponseBrands>, val imageLoadingServices: ImageLoadingServices):RecyclerView.Adapter<AdapterBrands.BannerViewHolder>() {
var onClickBrandId :OnClickBrandId?=null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerViewHolder {
     val view =LayoutInflater.from(parent.context).inflate(R.layout.item_brands,parent,false)
        return BannerViewHolder(view)
    }

    override fun onBindViewHolder(holder: BannerViewHolder, position: Int) {

        val itemBrands = brands[position]
        imageLoadingServices.loadImage(holder.imgBrands,itemBrands.image)
        holder.txtBrnds.text = itemBrands.name
        holder.itemView.setOnClickListener {
            onClickBrandId?.onClickBrand(itemBrands.id)
        }
    }

    override fun getItemCount(): Int =brands.size
    fun setOnClickBrand( onClickBrandId :OnClickBrandId)
    {
        this.onClickBrandId = onClickBrandId
    }
    class BannerViewHolder(itemView: View):RecyclerView.ViewHolder(itemView)
    {
        val imgBrands =itemView.findViewById<MyImageView>(R.id.img_brands)
        val txtBrnds =itemView.findViewById<TextView>(R.id.txt_brands)
    }


    interface OnClickBrandId
    {
        fun onClickBrand(brandId:Int)
    }
}