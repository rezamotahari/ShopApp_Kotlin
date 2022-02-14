package com.example.novinshop_project.feature.address

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.novinshop_project.R
import com.example.novinshop_project.data.ResponseAddress

class AdapterShowAddress(val listAddress:List<ResponseAddress>):RecyclerView.Adapter<AdapterShowAddress.AddressViewHolder>() {
var onCLickAdderss : OnClickItemAddress ?=null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddressViewHolder {
     val view =LayoutInflater.from(parent.context).inflate(R.layout.item_adress,parent,false)
        return AddressViewHolder(view)
    }

    override fun onBindViewHolder(holder: AddressViewHolder, position: Int) {

        val itemAddress = listAddress[position]
        holder.txtAddress.text = itemAddress.address
        holder.txtNameFamily.text= itemAddress.nameFamily
        holder.txtPhone.text = itemAddress.phone
        holder.txtPostalCode.text = itemAddress.poatalCode
        holder.itemView.setOnClickListener {

            onCLickAdderss!!.onClickAddress(itemAddress)
        }

    }

    override fun getItemCount(): Int =listAddress.size
    fun setOnCLickAddress(onCLickAdder : OnClickItemAddress)
    {
        this.onCLickAdderss=onCLickAdder
    }
    class AddressViewHolder(itemView: View):RecyclerView.ViewHolder(itemView)
    {
        val txtAddress =itemView.findViewById<TextView>(R.id.txt_adress)
        val txtNameFamily =itemView.findViewById<TextView>(R.id.txt_family)
        val txtPhone =itemView.findViewById<TextView>(R.id.txt_phone)
        val txtPostalCode =itemView.findViewById<TextView>(R.id.txt_postalcode)
      //  val txtCity =itemView.findViewById<TextView>(R.id.txt_propertiesChild_value)
        val txtState =itemView.findViewById<TextView>(R.id.txt_state)
    }
    interface  OnClickItemAddress{
        fun onClickAddress(address: ResponseAddress)
    }
}