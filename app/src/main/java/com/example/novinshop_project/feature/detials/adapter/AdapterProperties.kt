package com.example.novinshop_project.feature.detials.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.novinshop_project.R
import com.example.novinshop_project.data.PropertiesItem

class AdapterProperties(val items:List<PropertiesItem>):RecyclerView.Adapter<AdapterProperties.GalleryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryViewHolder {
     val view =LayoutInflater.from(parent.context).inflate(R.layout.item_properties,parent,false)
        return GalleryViewHolder(view)
    }

    override fun onBindViewHolder(holder: GalleryViewHolder, position: Int) {

        val itemProperties = items[position]
        holder.txtTitle.text = itemProperties.propertyName
        holder.txtValue.text = itemProperties.value
    }

    override fun getItemCount(): Int =items.size
    class GalleryViewHolder(itemView: View):RecyclerView.ViewHolder(itemView)
    {
        val txtTitle =itemView.findViewById<TextView>(R.id.txt_propertiesChild_title)
        val txtValue =itemView.findViewById<TextView>(R.id.txt_propertiesChild_value)
    }
}