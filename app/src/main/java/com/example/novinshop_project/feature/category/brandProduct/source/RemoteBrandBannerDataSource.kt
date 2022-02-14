package com.example.novinshop_project.feature.category.brandProduct.source

import com.example.novinshop_project.api.ApiService
import com.example.novinshop_project.data.ResponseBrandBanner
import com.example.novinshop_project.data.ResponseBrandProduct
import io.reactivex.Single

class RemoteBrandBannerDataSource(val apiService: ApiService):BrandBannerDataSource {
    override fun getBannerBrand(brandId: Int): Single<ResponseBrandBanner>  = apiService.getBannerBrand(brandId)
    override fun getBrandProduct(brandId: Int): Single<List<ResponseBrandProduct>> = apiService.getBrandProduct(brandId)
}