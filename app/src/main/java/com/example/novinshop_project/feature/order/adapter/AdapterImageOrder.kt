package com.example.novinshop_project.feature.order.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.novinshop_project.R
import com.example.novinshop_project.data.ImagesItemHistory
import com.example.novinshop_project.services.ImageLoadingServices
import com.example.novinshop_project.view.MyImageView

class AdapterImageOrder(val images:List<ImagesItemHistory>, val imageLoadingServices: ImageLoadingServices):RecyclerView.Adapter<AdapterImageOrder.ImagesOrderViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImagesOrderViewHolder {
     val view =LayoutInflater.from(parent.context).inflate(R.layout.item_image_order,parent,false)
        return ImagesOrderViewHolder(view)
    }

    override fun onBindViewHolder(holder: ImagesOrderViewHolder, position: Int) {

        val itemImage = images[position]
        imageLoadingServices.loadImage(holder.imgOrder,itemImage.url)
    }

    override fun getItemCount(): Int =images.size
    class ImagesOrderViewHolder(itemView: View):RecyclerView.ViewHolder(itemView)
    {
        val imgOrder =itemView.findViewById<MyImageView>(R.id.img_order)
    }
}