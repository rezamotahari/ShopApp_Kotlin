package com.example.novinshop_project.services

import android.widget.ImageView
import com.example.novinshop_project.view.MyImageView

interface ImageLoadingServices {
    fun loadImage(imageView: MyImageView,imageUrl:String?)
}