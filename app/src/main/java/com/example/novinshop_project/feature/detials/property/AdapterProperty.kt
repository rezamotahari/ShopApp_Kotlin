package com.example.novinshop_project.feature.detials.property

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.novinshop_project.R
import com.example.novinshop_project.data.ResponseProperty

class AdapterProperty(val property:List<ResponseProperty>):RecyclerView.Adapter<AdapterProperty.GalleryViewHolder>() {

    val viewPool =RecyclerView.RecycledViewPool()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryViewHolder {
     val view =LayoutInflater.from(parent.context).inflate(R.layout.item_property_parent,parent,false)
        return GalleryViewHolder(view)
    }

    override fun onBindViewHolder(holder: GalleryViewHolder, position: Int) {

        val itemProperty = property[position]
        holder.txtTitleProperty.text = itemProperty.mainCetegory
        val layoutManager = LinearLayoutManager(holder.rcChild.context,LinearLayoutManager.VERTICAL,false)
        layoutManager.initialPrefetchItemCount = itemProperty.property.size
        val adapterChild = AdapterPropertyChild(itemProperty.property)
        holder.rcChild.layoutManager = layoutManager
        holder.rcChild.adapter =adapterChild
        holder.rcChild.setRecycledViewPool(viewPool)

    }

    override fun getItemCount(): Int =property.size
    class GalleryViewHolder(itemView: View):RecyclerView.ViewHolder(itemView)
    {
        val txtTitleProperty =itemView.findViewById<TextView>(R.id.txt_parent)
        val rcChild =itemView.findViewById<RecyclerView>(R.id.rc_product_property_child)
    }
}