package com.example.novinshop_project.feature.category.brandProduct.source

import com.example.novinshop_project.data.ResponseBrandBanner
import com.example.novinshop_project.data.ResponseBrandProduct
import io.reactivex.Single

interface BrandBannerDataSource {

    fun getBannerBrand(brandId:Int):Single<ResponseBrandBanner>
    fun getBrandProduct(brandId:Int):Single<List<ResponseBrandProduct>>

}