package com.example.novinshop_project.feature.home.adapterHome

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.novinshop_project.R
import com.example.novinshop_project.data.ResponseCategoryHome
import com.example.novinshop_project.services.ImageLoadingServices
import com.example.novinshop_project.view.MyImageView
import com.google.android.material.navigation.NavigationBarItemView

class AdapterCategoryHome(val categories:List<ResponseCategoryHome>,val imageLoadingServices: ImageLoadingServices):RecyclerView.Adapter<AdapterCategoryHome.CateoryViewHolder>() {

    var onCclickSubCatLevel :OnClickSubCatLevel?=null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CateoryViewHolder {
 val view = LayoutInflater.from(parent.context).inflate(R.layout.item_category_home,parent,false)

        return CateoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CateoryViewHolder, position: Int) {
        val itemCategory= categories[position]
        imageLoadingServices.loadImage(holder.imgCategory,itemCategory.image)
        holder.txtTitleCategory.text=itemCategory.title

        holder.itemView.setOnClickListener {

            onCclickSubCatLevel!!.onClickSubcat(itemCategory.id)
        }
    }

    override fun getItemCount(): Int  = categories.size
    fun setSubCatLevel(onCclickSubCatLevel :OnClickSubCatLevel)
    {
        this.onCclickSubCatLevel = onCclickSubCatLevel
    }
    class CateoryViewHolder(itemView: View):RecyclerView.ViewHolder(itemView)
    {
        val imgCategory=itemView.findViewById<MyImageView>(R.id.img_category_home)
        val txtTitleCategory= itemView.findViewById<TextView>(R.id.txt_title_category_home)
    }

    interface OnClickSubCatLevel{
        fun onClickSubcat(subcatId:Int)
    }
}