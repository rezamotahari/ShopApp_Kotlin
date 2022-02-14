package com.example.novinshop_project.feature.profile.favourite.source

import com.example.novinshop_project.data.ResponseFavourite
import io.reactivex.Single

interface FavouriteDataSource {
    fun getFavourite():Single<List<ResponseFavourite>>
}