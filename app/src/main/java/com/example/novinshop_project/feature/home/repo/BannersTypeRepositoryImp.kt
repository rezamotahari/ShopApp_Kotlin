package com.example.novinshop_project.feature.home.repo

import com.example.novinshop_project.data.ResponseBannersType
import com.example.novinshop_project.feature.home.source.BannersTypeDataSource
import io.reactivex.Single

class BannersTypeRepositoryImp(val  remoteBannersTypeDataSource: BannersTypeDataSource ) :BannersTypeRepository{
    override fun getBannersType(): Single<List<ResponseBannersType>>  = remoteBannersTypeDataSource.getBannersType()
}