package com.example.novinshop_project.feature.profile.favourite.source

import com.example.novinshop_project.api.ApiService
import com.example.novinshop_project.data.ResponseFavourite
import io.reactivex.Single

class RemoteFavouriteDataSource(val apiService: ApiService):FavouriteDataSource {
    override fun getFavourite(): Single<List<ResponseFavourite>> = apiService.getFavourite()
}