package com.example.novinshop_project.feature.category.brandProduct.repo

import com.example.novinshop_project.data.ResponseBrandBanner
import com.example.novinshop_project.data.ResponseBrandProduct
import com.example.novinshop_project.feature.category.brandProduct.source.BrandBannerDataSource
import com.example.novinshop_project.feature.category.brandProduct.source.RemoteBrandBannerDataSource
import io.reactivex.Single

class BrandBannerRepositoryImpl(val remoteBrandBannerDataSource: BrandBannerDataSource):BrandBannerRepository {
    override fun getBannerBrand(brandId: Int): Single<ResponseBrandBanner>  = remoteBrandBannerDataSource.getBannerBrand(brandId)
    override fun getBrandProduct(brandId: Int): Single<List<ResponseBrandProduct>>  = remoteBrandBannerDataSource.getBrandProduct(brandId)
}