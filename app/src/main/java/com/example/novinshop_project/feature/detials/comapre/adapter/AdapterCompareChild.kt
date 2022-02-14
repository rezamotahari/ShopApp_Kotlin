package com.example.novinshop_project.feature.detials.comapre.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.novinshop_project.R
import com.example.novinshop_project.data.ValuesItem

class AdapterCompareChild(val childComapre:List<ValuesItem>):RecyclerView.Adapter<AdapterCompareChild.CategoryCompareViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryCompareViewHolder {
     val view =LayoutInflater.from(parent.context).inflate(R.layout.item_compare_chile,parent,false)
        return CategoryCompareViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryCompareViewHolder, position: Int) {

        val itemChild = childComapre[position]

       holder.txtPrOne.text = itemChild.property1
       holder.txtPrTwo.text = itemChild.property2


    }

    override fun getItemCount(): Int =childComapre.size


    class CategoryCompareViewHolder(itemView: View):RecyclerView.ViewHolder(itemView)
    {
        val txtPrOne =itemView.findViewById<TextView>(R.id.txt_compareItem_one)
        val txtPrTwo =itemView.findViewById<TextView>(R.id.txt_compareItem_two)
    }

}