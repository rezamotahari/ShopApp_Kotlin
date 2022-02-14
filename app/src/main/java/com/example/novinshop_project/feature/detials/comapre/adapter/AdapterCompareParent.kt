package com.example.novinshop_project.feature.detials.comapre.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.novinshop_project.R
import com.example.novinshop_project.data.PropertyItemProduct

class AdapterCompareParent(val parentCompare:List<PropertyItemProduct>):RecyclerView.Adapter<AdapterCompareParent.CategoryCompareViewHolder>() {

    private val viewPool =RecyclerView.RecycledViewPool()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryCompareViewHolder {
     val view =LayoutInflater.from(parent.context).inflate(R.layout.item_compare_parent,parent,false)
        return CategoryCompareViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryCompareViewHolder, position: Int) {

        val itemParent = parentCompare[position]

        holder.txtMainCategory.text = itemParent.mainCetegory
        val linearLayoutManager =LinearLayoutManager(holder.rcValues.context,LinearLayoutManager.VERTICAL,
        false)
       linearLayoutManager.initialPrefetchItemCount = itemParent.values!!.size
        val itemDecoration :RecyclerView.ItemDecoration = DividerItemDecoration(holder.rcValues.context,DividerItemDecoration.VERTICAL)
        val adapterCompareChild =AdapterCompareChild(itemParent.values!!)
        holder.rcValues.layoutManager = linearLayoutManager
        holder.rcValues.adapter = adapterCompareChild
        holder.rcValues.setRecycledViewPool(viewPool)
        holder.rcValues.addItemDecoration(itemDecoration)


    }

    override fun getItemCount(): Int =parentCompare.size


    class CategoryCompareViewHolder(itemView: View):RecyclerView.ViewHolder(itemView)
    {
        val txtMainCategory =itemView.findViewById<TextView>(R.id.txt_parent)
        val rcValues =itemView.findViewById<RecyclerView>(R.id.rc_pr)
    }

}