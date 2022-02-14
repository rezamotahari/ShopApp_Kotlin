package com.example.novinshop_project.feature.home.adapterHome

import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.novinshop_project.R
import com.example.novinshop_project.data.ResponseAmazingProduct
import com.example.novinshop_project.services.ImageLoadingServices
import com.example.novinshop_project.utils.FreePercent
import com.example.novinshop_project.utils.PriceConverter
import com.example.novinshop_project.view.MyImageView

const val PRODUCT_ITEM_FIRST = 1
const val PRODUCT_ITEM_AMZAING = 2

class AdapterAmazing(
    val amazingProduct: List<ResponseAmazingProduct>,
    val imageLoadingServices: ImageLoadingServices
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    lateinit var onClickProductItem: OnClickProductItem

    fun setOnCLickProductItem(onClickProductItem: OnClickProductItem)
    {
        this.onClickProductItem = onClickProductItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        if (viewType == PRODUCT_ITEM_FIRST) {
            val views = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_first_amazing, parent, false)
            return FirstItemViewHoder(views)
        } else {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_amazing_product, parent, false)
            return AmazingProductViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        if (getItemViewType(position) == PRODUCT_ITEM_FIRST) {
            (holder as FirstItemViewHoder).imgFirst.setActualImageResource(R.drawable.produt_item_image)
        } else {
            val itemAmazing = amazingProduct[position - 1]
            imageLoadingServices.loadImage(
                (holder as AmazingProductViewHolder).imgProduct,
                itemAmazing.image
            )
            holder.txtPrice.paintFlags = android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
            holder.txtPrice.text = PriceConverter.priceConverter(itemAmazing.price.toString())
            holder.txtCount.text = PriceConverter.sellsCount(itemAmazing.sellsCount.toString())
            holder.txtOffPercent.text =
                PriceConverter.freePercent(itemAmazing.offPercent.toString())
            holder.txtFreePrice.text =
                PriceConverter.priceConverter(itemAmazing.amazingPrice.toString())
            holder.txtTitle.text = itemAmazing.name.toString()
            holder.prAmazing.max = itemAmazing.number
            holder.prAmazing.progress = itemAmazing.sellsCount
            val countDownTimer: CountDownTimer =
                object : CountDownTimer(itemAmazing.amazingTime, 1000) {
                    override fun onTick(millisUntilFinished: Long) {
                        val hour = millisUntilFinished / 3600000 % 24
                        val min = millisUntilFinished / 60000 % 60
                        val sec = millisUntilFinished / 1000 % 60
                        holder.txtTime.text =
                            holder.itemView.context.getString(R.string.time, hour, min, sec)

                    }

                    override fun onFinish() {
                        holder.txtTime.text = "تخفیف تمام شد"
                    }

                }
            countDownTimer.start()

            holder.itemView.setOnClickListener {

                onClickProductItem.onClickProduct(itemAmazing.id)
            }

        }
    }

    override fun getItemCount(): Int = (amazingProduct.size) + 1

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) {
            PRODUCT_ITEM_FIRST
        } else {
            PRODUCT_ITEM_AMZAING
        }
    }

    inner class AmazingProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgProduct = itemView.findViewById<MyImageView>(R.id.img_amazing_product)
        val txtTitle = itemView.findViewById<TextView>(R.id.txt_title_product)
        val txtPrice = itemView.findViewById<TextView>(R.id.txt_price)
        val txtFreePrice = itemView.findViewById<TextView>(R.id.txt_free_price)
        val txtOffPercent = itemView.findViewById<TextView>(R.id.txt_free)
        val txtTime = itemView.findViewById<TextView>(R.id.txt_time)
        val txtCount = itemView.findViewById<TextView>(R.id.txt_count)
        val prAmazing = itemView.findViewById<ProgressBar>(R.id.progress_amazing)
    }

    class FirstItemViewHoder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgFirst = itemView.findViewById<MyImageView>(R.id.img_first)

    }

    interface OnClickProductItem {
        fun onClickProduct(productId: Int)

    }
}