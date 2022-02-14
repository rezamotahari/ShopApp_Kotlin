package com.example.novinshop_project.feature.detials.property

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.novinshop_project.R
import com.example.novinshop_project.data.PropertyItem

class AdapterPropertyChild(val propertyChild:List<PropertyItem>):RecyclerView.Adapter<AdapterPropertyChild.PropertyChildeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PropertyChildeViewHolder {
     val view =LayoutInflater.from(parent.context).inflate(R.layout.item_property_child,parent,false)
        return PropertyChildeViewHolder(view)
    }

    override fun onBindViewHolder(holder: PropertyChildeViewHolder, position: Int) {

        val itemProperty = propertyChild[position]
        holder.txtName.text = itemProperty.title
        holder.txtValue.text = itemProperty.property
    }

    override fun getItemCount(): Int =propertyChild.size
    class PropertyChildeViewHolder(itemView: View):RecyclerView.ViewHolder(itemView)
    {
        val txtName =itemView.findViewById<TextView>(R.id.txt_name_peroduct)
        val txtValue =itemView.findViewById<TextView>(R.id.txt_value_product)
    }
}