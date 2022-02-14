package com.example.novinshop_project.feature.detials.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.novinshop_project.R
import com.example.novinshop_project.data.ProductColorsItem
import com.example.novinshop_project.data.SimilarItem
import com.example.novinshop_project.services.ImageLoadingServices
import com.example.novinshop_project.view.MyImageView
import com.google.android.material.card.MaterialCardView

class AdapterSimilar(val similars:List<SimilarItem>,val imageLoadingServices: ImageLoadingServices):RecyclerView.Adapter<AdapterSimilar.GalleryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryViewHolder {
     val view =LayoutInflater.from(parent.context).inflate(R.layout.item_similar,parent,false)
        return GalleryViewHolder(view)
    }

    override fun onBindViewHolder(holder: GalleryViewHolder, position: Int) {

        val itemSimilar = similars[position]
        imageLoadingServices.loadImage(holder.imgSimilar,itemSimilar.url)
        holder.txtName.text=itemSimilar.name

    }

    override fun getItemCount(): Int =similars.size
    class GalleryViewHolder(itemView: View):RecyclerView.ViewHolder(itemView)
    {
        val imgSimilar =itemView.findViewById<MyImageView>(R.id.img_similar)
        val txtName =itemView.findViewById<TextView>(R.id.txt_similar)
    }
}