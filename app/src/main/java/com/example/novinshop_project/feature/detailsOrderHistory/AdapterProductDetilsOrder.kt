package com.example.novinshop_project.feature.detailsOrderHistory

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.novinshop_project.R
import com.example.novinshop_project.data.OrderDetailItem
import com.example.novinshop_project.services.ImageLoadingServices
import com.example.novinshop_project.utils.PriceConverter
import com.example.novinshop_project.view.MyImageView

class AdapterProductDetilsOrder(val productsOrder:List<OrderDetailItem>, val imageLoadingServices: ImageLoadingServices):RecyclerView.Adapter<AdapterProductDetilsOrder.DetilsOrderViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetilsOrderViewHolder {
     val view =LayoutInflater.from(parent.context).inflate(R.layout.item_detils_order,parent,false)
        return DetilsOrderViewHolder(view)
    }

    override fun onBindViewHolder(holder: DetilsOrderViewHolder, position: Int) {

        val itemOrder = productsOrder[position]
        imageLoadingServices.loadImage(holder.imgOrder,itemOrder.image)
        holder.txtName.text = itemOrder.name
        holder.txtPrice.text =PriceConverter.priceConverter(itemOrder.price)
    }

    override fun getItemCount(): Int =productsOrder.size
    class DetilsOrderViewHolder(itemView: View):RecyclerView.ViewHolder(itemView)
    {
        val imgOrder =itemView.findViewById<MyImageView>(R.id.img_detils_order)
        val txtName =itemView.findViewById<TextView>(R.id.txt_name_product_detils_order)
        val txtPrice =itemView.findViewById<TextView>(R.id.txt_price_detils_order)
    }
}