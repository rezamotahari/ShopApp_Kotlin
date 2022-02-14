package com.example.novinshop_project.feature.category.subCat.source

import com.example.novinshop_project.data.ResponseBrands
import com.example.novinshop_project.data.ResponsePopularCat
import com.example.novinshop_project.data.ResponseSubCat
import io.reactivex.Single

interface SubCatDataSource {
    fun getSubCat(catId:Int):Single<List<ResponseSubCat>>
    fun getBrands(catId:Int):Single<List<ResponseBrands>>
    fun getPopularCat(catId:Int):Single<List<ResponsePopularCat>>


}