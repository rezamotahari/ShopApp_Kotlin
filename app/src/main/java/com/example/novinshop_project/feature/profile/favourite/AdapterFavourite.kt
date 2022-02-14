package com.example.novinshop_project.feature.profile.favourite

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.novinshop_project.R
import com.example.novinshop_project.data.ResponseFavourite
import com.example.novinshop_project.services.ImageLoadingServices
import com.example.novinshop_project.view.MyImageView

class AdapterFavourite(val favourite:List<ResponseFavourite>, val imageLoadingServices: ImageLoadingServices):RecyclerView.Adapter<AdapterFavourite.FavouriteViewHolder>() {

    var onClickFavourite :OnCLickItemFavourite ?=null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavouriteViewHolder {
     val view =LayoutInflater.from(parent.context).inflate(R.layout.item_favourite,parent,false)
        return FavouriteViewHolder(view)
    }

    override fun onBindViewHolder(holder: FavouriteViewHolder, position: Int) {

        val itemFavourite = favourite[position]
        imageLoadingServices.loadImage(holder.imgFavourite,itemFavourite.url)
        holder.txtName.text = itemFavourite.name
        holder.itemView.setOnClickListener {

            onClickFavourite!!.onClickFavourite(itemFavourite.id.toInt())
        }

    }

    override fun getItemCount(): Int =favourite.size
    fun setOnClickItemFavourite(onClickFavourite :OnCLickItemFavourite)
    {
        this.onClickFavourite = onClickFavourite
    }
    class FavouriteViewHolder(itemView: View):RecyclerView.ViewHolder(itemView)
    {
        val imgFavourite =itemView.findViewById<MyImageView>(R.id.img_favourite)
        val txtName =itemView.findViewById<TextView>(R.id.txt_name_favourite)

    }
    interface  OnCLickItemFavourite
    {
        fun onClickFavourite(productId: Int)
    }
}