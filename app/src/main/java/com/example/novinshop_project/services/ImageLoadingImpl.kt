package com.example.novinshop_project.services

import android.widget.ImageView
import com.example.novinshop_project.view.MyImageView
import com.facebook.drawee.view.SimpleDraweeView

class ImageLoadingImpl:ImageLoadingServices {
    override fun loadImage(imageView: MyImageView, imageUrl: String?) {

        if (imageView is SimpleDraweeView)
        {
            imageView.setImageURI(imageUrl)
        }
    }
}