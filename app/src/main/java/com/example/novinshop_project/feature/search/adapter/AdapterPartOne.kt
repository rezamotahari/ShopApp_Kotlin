package com.example.novinshop_project.feature.search.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.novinshop_project.R
import com.example.novinshop_project.data.Part1Item

class AdapterPartOne(val partOne:List<Part1Item>):RecyclerView.Adapter<AdapterPartOne.PartOneViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PartOneViewHolder {
 val view = LayoutInflater.from(parent.context).inflate(R.layout.item_part_one,parent,false)

        return PartOneViewHolder(view)
    }

    override fun onBindViewHolder(holder: PartOneViewHolder, position: Int) {
        val itemPartOne= partOne[position]
        holder.txtCategory.text=itemPartOne.title


    }

    override fun getItemCount(): Int  = partOne.size

    class PartOneViewHolder(itemView: View):RecyclerView.ViewHolder(itemView)
    {
        val txtCategory= itemView.findViewById<TextView>(R.id.txt_part_one)
    }


}