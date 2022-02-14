package com.example.novinshop_project.utils

import java.text.DecimalFormat

object PriceConverter {

    fun priceConverter(price :String):String{
        val decimalFormat = DecimalFormat("###,###")
        val  prices=decimalFormat.format(Integer.valueOf(price))
        return "$prices تومان "
    }
    fun freePercent(free:String):String
    {
        return " % $free"
    }
    fun sellsCount(sells:String):String{
        return " $sells فروش رفته "
    }
}