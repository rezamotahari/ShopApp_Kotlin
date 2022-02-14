package com.example.novinshop_project.feature.allAmazing.adapter

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.novinshop_project.R
import com.example.novinshop_project.data.ResponseBrandProduct
import com.example.novinshop_project.data.ResponseSortAmazing
import com.example.novinshop_project.services.ImageLoadingServices
import com.example.novinshop_project.utils.PriceConverter
import com.example.novinshop_project.view.MyImageView

class AdapterAllAmazing(val brands:List<ResponseSortAmazing>, val imageLoadingServices: ImageLoadingServices):RecyclerView.Adapter<AdapterAllAmazing.BrandProductViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BrandProductViewHolder {
     val view =LayoutInflater.from(parent.context).inflate(R.layout.item_brand_prodduct,parent,false)
        return BrandProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: BrandProductViewHolder, position: Int) {

        val itemBrands = brands[position]
        imageLoadingServices.loadImage(holder.imgProducts,itemBrands.image)
        holder.txtNameProduct.text = itemBrands.name
        if (itemBrands.offPercent !="0") {
            holder.txtPrice.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
        }
        holder.lnrFree.setVisibility(if (itemBrands.offPercent !="0") View.VISIBLE else View.GONE)
        holder.txtOffPrice.setVisibility(if (itemBrands.offPercent !="0") View.VISIBLE else View.GONE)
        holder.txtOffPercent.setVisibility(if (itemBrands.offPercent !="0") View.VISIBLE else View.GONE)
        holder.txtOffPercent.text = itemBrands.offPercent
        holder.txtPrice.text = PriceConverter.priceConverter(itemBrands.price.toString())
        holder.txtOffPrice.text = PriceConverter.priceConverter(itemBrands.amazingPrice.toString())
        holder.txtOffPercent.text = PriceConverter.freePercent(itemBrands.offPercent)

    }

    override fun getItemCount(): Int =brands.size
    class BrandProductViewHolder(itemView: View):RecyclerView.ViewHolder(itemView)
    {
        val imgProducts =itemView.findViewById<MyImageView>(R.id.img_brand_product)
        val txtNameProduct =itemView.findViewById<TextView>(R.id.txt_name_product)
        val txtOffPercent =itemView.findViewById<TextView>(R.id.txt_free)
        val txtPrice =itemView.findViewById<TextView>(R.id.txt_price)
        val txtOffPrice =itemView.findViewById<TextView>(R.id.txt_free_price)
        val lnrFree =itemView.findViewById<LinearLayout>(R.id.lnr_free)
    }
}