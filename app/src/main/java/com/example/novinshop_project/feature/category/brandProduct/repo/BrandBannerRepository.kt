package com.example.novinshop_project.feature.category.brandProduct.repo

import com.example.novinshop_project.data.ResponseBrandBanner
import com.example.novinshop_project.data.ResponseBrandProduct
import io.reactivex.Single

interface BrandBannerRepository {

    fun getBannerBrand(brandId:Int):Single<ResponseBrandBanner>
    fun getBrandProduct(brandId:Int):Single<List<ResponseBrandProduct>>
}