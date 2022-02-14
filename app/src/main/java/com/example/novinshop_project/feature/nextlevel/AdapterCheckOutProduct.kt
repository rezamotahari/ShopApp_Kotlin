package com.example.novinshop_project.feature.nextlevel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.novinshop_project.R
import com.example.novinshop_project.data.ProductItemDeliveriesItem
import com.example.novinshop_project.services.ImageLoadingServices
import com.example.novinshop_project.view.MyImageView

class AdapterCheckOutProduct(val checkOut:List<ProductItemDeliveriesItem>, val imageLoadingServices: ImageLoadingServices):RecyclerView.Adapter<AdapterCheckOutProduct.BannerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerViewHolder {
     val view =LayoutInflater.from(parent.context).inflate(R.layout.item_checkout_product,parent,false)
        return BannerViewHolder(view)
    }

    override fun onBindViewHolder(holder: BannerViewHolder, position: Int) {

        val itemCheckOut = checkOut[position]
        imageLoadingServices.loadImage(holder.imgProduct,itemCheckOut.iamge)
        holder.txtCount.text=itemCheckOut.count
        holder.txtName.text = itemCheckOut.name
    }

    override fun getItemCount(): Int =checkOut.size
    class BannerViewHolder(itemView: View):RecyclerView.ViewHolder(itemView)
    {
        val imgProduct =itemView.findViewById<MyImageView>(R.id.img_product)
        val txtName =itemView.findViewById<TextView>(R.id.txt_name_product)
        val txtCount =itemView.findViewById<TextView>(R.id.txt_count_product)
    }
}