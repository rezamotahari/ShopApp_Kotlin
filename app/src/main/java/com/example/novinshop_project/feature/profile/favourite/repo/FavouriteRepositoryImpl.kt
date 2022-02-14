package com.example.novinshop_project.feature.profile.favourite.repo

import com.example.novinshop_project.data.ResponseFavourite
import com.example.novinshop_project.feature.profile.favourite.source.FavouriteDataSource
import io.reactivex.Single

class FavouriteRepositoryImpl(val remoteFavouriteDataSource: FavouriteDataSource ):FavouriteRepository {
    override fun getFavourite(): Single<List<ResponseFavourite>>  = remoteFavouriteDataSource.getFavourite()
}