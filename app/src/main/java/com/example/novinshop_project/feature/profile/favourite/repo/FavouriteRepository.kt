package com.example.novinshop_project.feature.profile.favourite.repo

import com.example.novinshop_project.data.ResponseFavourite
import io.reactivex.Single

interface FavouriteRepository {
    fun getFavourite():Single<List<ResponseFavourite>>
}