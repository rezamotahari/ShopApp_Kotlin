package com.example.novinshop_project.feature.order.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.novinshop_project.R
import com.example.novinshop_project.data.ResponseAddress
import com.example.novinshop_project.data.ResponseOrderHistory
import com.example.novinshop_project.feature.detials.property.AdapterPropertyChild
import com.example.novinshop_project.services.ImageLoadingServices
import com.example.novinshop_project.utils.PriceConverter

class AdapterOrderHistory(val orders:List<ResponseOrderHistory>,val imageLoadingServices: ImageLoadingServices):RecyclerView.Adapter<AdapterOrderHistory.OrderHistoryViewModel>() {
    val viewPool =RecyclerView.RecycledViewPool()
    var onClickOrderHistory :OnClickOrderHistory?=null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderHistoryViewModel {
     val view =LayoutInflater.from(parent.context).inflate(R.layout.item_order,parent,false)
        return OrderHistoryViewModel(view)
    }

    override fun onBindViewHolder(holder: OrderHistoryViewModel, position: Int) {

        val itemOrder = orders[position]
        holder.txtCode.text = itemOrder.refId
        holder.txtDate.text= itemOrder.dateTime
        holder.txtPrice.text =PriceConverter.priceConverter(itemOrder.totalPrice)
        val layoutManager = LinearLayoutManager(holder.rcImage.context,
            LinearLayoutManager.HORIZONTAL,false)
        layoutManager.initialPrefetchItemCount = itemOrder.images.size
        val adapterImageOrder = AdapterImageOrder(itemOrder.images,imageLoadingServices )
        holder.rcImage.layoutManager = layoutManager
        holder.rcImage.adapter =adapterImageOrder
        holder.rcImage.setRecycledViewPool(viewPool)
        holder.itemView.setOnClickListener {
            onClickOrderHistory!!.onClickOrder(itemOrder.refId)
        }

    }

    override fun getItemCount(): Int =orders.size

    fun setOnclickOrderHistory(onClickOrderHistory :OnClickOrderHistory)
    {
        this.onClickOrderHistory = onClickOrderHistory
    }
    class OrderHistoryViewModel(itemView: View):RecyclerView.ViewHolder(itemView)
    {
        val txtDate =itemView.findViewById<TextView>(R.id.txt_date_order)
        val txtCode =itemView.findViewById<TextView>(R.id.txt_code)
        val txtPrice =itemView.findViewById<TextView>(R.id.txt_price_order)
        val rcImage =itemView.findViewById<RecyclerView>(R.id.rc_image_order)

    }
interface  OnClickOrderHistory
{
    fun onClickOrder(refId:String)
}
}