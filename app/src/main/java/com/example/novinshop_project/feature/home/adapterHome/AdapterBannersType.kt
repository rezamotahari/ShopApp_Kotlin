package com.example.novinshop_project.feature.home.adapterHome

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.novinshop_project.R
import com.example.novinshop_project.data.ResponseBannersType
import com.example.novinshop_project.services.ImageLoadingServices
import com.example.novinshop_project.view.MyImageView

class AdapterBannersType(val types:List<ResponseBannersType>, val imageLoadingServices: ImageLoadingServices):RecyclerView.Adapter<AdapterBannersType.TypeViewHolder>() {

    lateinit var onClickBannersType: OnClickBannersType
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TypeViewHolder {
     val view =LayoutInflater.from(parent.context).inflate(R.layout.item_banners_type,parent,false)
        return TypeViewHolder(view)
    }

    @JvmName("setOnClickBannersType1")
    fun setOnClickBannersType(onClickBannersType: OnClickBannersType)
    {
        this.onClickBannersType = onClickBannersType
    }
    override fun onBindViewHolder(holder: TypeViewHolder, position: Int) {

        val itemType = types[position]
        imageLoadingServices.loadImage(holder.imgType,itemType.image)

        holder.itemView.setOnClickListener {

            onClickBannersType.onClickTypes(itemType.type,itemType.link)
        }
    }

    override fun getItemCount(): Int =types.size
    class TypeViewHolder(itemView: View):RecyclerView.ViewHolder(itemView)
    {
        val imgType =itemView.findViewById<MyImageView>(R.id.img_banners_type_main)
    }


    interface OnClickBannersType{

        fun onClickTypes(type :String,link:String)
    }

}