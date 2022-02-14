package com.example.novinshop_project.feature.category.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.novinshop_project.R
import com.example.novinshop_project.data.ResponsePopularCat
import com.example.novinshop_project.services.ImageLoadingServices
import com.example.novinshop_project.utils.PriceConverter
import com.example.novinshop_project.view.MyImageView

class AdapterPopularCat(val popular:List<ResponsePopularCat>, val imageLoadingServices: ImageLoadingServices):RecyclerView.Adapter<AdapterPopularCat.PopularCatViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularCatViewHolder {
     val view =LayoutInflater.from(parent.context).inflate(R.layout.item_popular_categorty_brands,parent,false)
        return PopularCatViewHolder(view)
    }

    override fun onBindViewHolder(holder: PopularCatViewHolder, position: Int) {

        val itemPopualrCat = popular[position]
        imageLoadingServices.loadImage(holder.imgPopularCat,itemPopualrCat.image)
        holder.txtPrice.text =PriceConverter.priceConverter(itemPopualrCat.price.toString())
        holder.txtTitleCat.text = itemPopualrCat.name
    }

    override fun getItemCount(): Int =popular.size
    class PopularCatViewHolder(itemView: View):RecyclerView.ViewHolder(itemView)
    {
        val imgPopularCat =itemView.findViewById<MyImageView>(R.id.img_similar)
        val txtTitleCat =itemView.findViewById<TextView>(R.id.txt_similar)
        val txtPrice =itemView.findViewById<TextView>(R.id.txt_price)
    }
}