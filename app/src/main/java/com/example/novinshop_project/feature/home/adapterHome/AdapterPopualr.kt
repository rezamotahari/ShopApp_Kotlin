package com.example.novinshop_project.feature.home.adapterHome

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.novinshop_project.R
import com.example.novinshop_project.data.ResponsePopular
import com.example.novinshop_project.services.ImageLoadingServices
import com.example.novinshop_project.view.MyImageView

class AdapterPopualr(val populars:List<ResponsePopular>, val imageLoadingServices: ImageLoadingServices):RecyclerView.Adapter<AdapterPopualr.PopularsViewHolder>() {


    lateinit  var onclickItemProductPopular :OnclickItemProductPopular

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularsViewHolder {
     val view =LayoutInflater.from(parent.context).inflate(R.layout.item_popular,parent,false)
        return PopularsViewHolder(view)
    }

    override fun onBindViewHolder(holder: PopularsViewHolder, position: Int) {

        val itemPopular = populars[position]
        imageLoadingServices.loadImage(holder.imgPopular,itemPopular.image)

        holder.itemView.setOnClickListener {
            onclickItemProductPopular?.onClickPopularItem(itemPopular.id)

        }
    }

    override fun getItemCount(): Int =populars.size
    @JvmName("setOnclickItemProductPopular1")
    fun setOnclickItemProductPopular(onclickItemProductPopular : OnclickItemProductPopular)
    {
        this.onclickItemProductPopular = onclickItemProductPopular
    }
    class PopularsViewHolder(itemView: View):RecyclerView.ViewHolder(itemView)
    {
        val imgPopular =itemView.findViewById<MyImageView>(R.id.img_popular_main)
    }
    interface OnclickItemProductPopular{
      fun  onClickPopularItem(idProduct:Int)
    }
}