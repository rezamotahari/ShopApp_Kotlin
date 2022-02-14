package com.example.novinshop_project.feature.cart.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import com.example.novinshop_project.R
import com.example.novinshop_project.data.ProductItemItem
import com.example.novinshop_project.data.ResponseCartList
import com.example.novinshop_project.data.ResponseCount
import com.example.novinshop_project.services.ImageLoadingServices
import com.example.novinshop_project.utils.PriceConverter
import com.example.novinshop_project.view.MyImageView

const val CARTPRODUTC = 0
const val PAYBLCECART = 1

class AdapterCartItem(
    val cartItems: ResponseCartList,
    val imageLoadingServices: ImageLoadingServices
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var publicTotalAllPrice: Int? = null
    var publickAllOffPrIce: Int? = null
    var publickAllPayablePrice: Int? = null
    var onCartItemClick:OnCartItemClick?=null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return if (viewType == CARTPRODUTC) {
            CartItemViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_cart, parent, false)
            )
        } else {
            PayblaeViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_paybale_price, parent, false)
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == CARTPRODUTC) {
            val cartProduct = cartItems.productItem[position]
            (holder as CartItemViewHolder).txtName.text = cartProduct.name
            holder.txtColor.text = cartProduct.color
            holder.txtSize.text = cartProduct.size
            holder.txtPrice.text = PriceConverter.priceConverter(cartProduct.price)
            holder.txtCount.text = cartProduct.count
            holder.txtOffPercent.text = PriceConverter.freePercent(cartProduct.offPercent)
            holder.txtOffPrice.text = PriceConverter.priceConverter(cartProduct.offPrice.toString())
            holder.txtPayblePrice.text =
                PriceConverter.priceConverter(cartProduct.totalProductPrice.toString())
            imageLoadingServices.loadImage(holder.imgProduct, cartProduct.iamge)

            holder.imgRemove.setOnClickListener {

                onCartItemClick?.onRemoveItem(cartProduct)

            }
            holder.imgSum.setOnClickListener {

                onCartItemClick!!.sumItem(cartProduct,cartProduct.count.toInt())
            }
            holder.imgMinus.setOnClickListener {

                onCartItemClick!!.minusItem(cartProduct,cartProduct.count.toInt())
            }

        } else {
            (holder as PayblaeViewHolder).txtAllOffPrice.text =
                PriceConverter.priceConverter(publickAllOffPrIce.toString())
            holder.txtAllpayblaePrice.text =
                PriceConverter.priceConverter(publickAllPayablePrice.toString())
            holder.txtTotalAllPrice.text =
                PriceConverter.priceConverter(publicTotalAllPrice.toString())
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == cartItems.productItem.size) {
            PAYBLCECART
        } else {
            CARTPRODUTC
        }
    }

    override fun getItemCount(): Int = (cartItems.productItem.size) + 1

    fun setOnClikcItemCart(onCartItemClick:OnCartItemClick)
    {
        this.onCartItemClick = onCartItemClick
    }

    fun sumItemCount(cartItem: ProductItemItem,count: Int)
    {
        val cartList :MutableList<ProductItemItem> = cartItems.productItem as MutableList<ProductItemItem>
        val index = cartList.indexOf(cartItem)
        val newCount = count + 1
        cartItem.count = newCount.toString()
        cartList.set(index,cartItem)
        notifyItemChanged(index)
    }
    fun minusItemCount(cartItem: ProductItemItem,count: Int)
    {
        val cartList :MutableList<ProductItemItem> = cartItems.productItem as MutableList<ProductItemItem>
        val index = cartList.indexOf(cartItem)
        val newCount = count - 1
        cartItem.count = newCount.toString()
        cartList.set(index,cartItem)
        notifyItemChanged(index)
    }
    class CartItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgProduct = itemView.findViewById<MyImageView>(R.id.img_product_cart)
        val imgRemove = itemView.findViewById<ImageView>(R.id.img_remove)
        val imgSum = itemView.findViewById<ImageView>(R.id.img_sum)
        val imgMinus = itemView.findViewById<ImageView>(R.id.img_minus)
        val txtName = itemView.findViewById<TextView>(R.id.txt_name_product)
        val txtColor = itemView.findViewById<TextView>(R.id.txt_color_product)
        val txtSize = itemView.findViewById<TextView>(R.id.txt_size_product)
        val txtPrice = itemView.findViewById<TextView>(R.id.txt_price_product)
        val txtCount = itemView.findViewById<TextView>(R.id.txt_count_cart)
        val txtOffPercent = itemView.findViewById<TextView>(R.id.txt_off_percent)
        val txtOffPrice = itemView.findViewById<TextView>(R.id.txt_off_price)
        val txtPayblePrice = itemView.findViewById<TextView>(R.id.txt_payblae_price)


    }

    class PayblaeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtTotalAllPrice = itemView.findViewById<TextView>(R.id.txt_all_totoal_price)
        val txtAllOffPrice = itemView.findViewById<TextView>(R.id.txt_all_off_price)
        val txtAllpayblaePrice = itemView.findViewById<TextView>(R.id.txt_all_payble_price)
    }
    interface OnCartItemClick
    {
        fun onRemoveItem(cartItem: ProductItemItem)
        fun sumItem(cartItem: ProductItemItem, count :Int)
        fun minusItem(cartItem: ProductItemItem, count :Int)
    }
}