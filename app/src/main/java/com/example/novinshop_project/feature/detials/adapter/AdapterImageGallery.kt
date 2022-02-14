package com.example.novinshop_project.feature.detials.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.novinshop_project.R
import com.example.novinshop_project.data.ImagesItem
import com.example.novinshop_project.services.ImageLoadingServices
import com.example.novinshop_project.view.MyImageView
import com.squareup.picasso.Picasso

class AdapterImageGallery(val gallery:List<ImagesItem>, val imageLoadingServices: ImageLoadingServices):RecyclerView.Adapter<AdapterImageGallery.GalleryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryViewHolder {
     val view =LayoutInflater.from(parent.context).inflate(R.layout.item_galley_images,parent,false)
        return GalleryViewHolder(view)
    }

    override fun onBindViewHolder(holder: GalleryViewHolder, position: Int) {

        val itemGallery = gallery[position]
//        imageLoadingServices.loadImage(holder.imgGallery,itemGallery.image)
        Picasso.get()
            .load(itemGallery.image)
            .into(holder.imgGallery)
    }

    override fun getItemCount(): Int =gallery.size
    class GalleryViewHolder(itemView: View):RecyclerView.ViewHolder(itemView)
    {
        val imgGallery =itemView.findViewById<MyImageView>(R.id.img_gallery)
    }
}