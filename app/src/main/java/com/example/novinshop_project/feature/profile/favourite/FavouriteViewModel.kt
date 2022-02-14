package com.example.novinshop_project.feature.profile.favourite

import androidx.lifecycle.MutableLiveData
import com.example.novinshop_project.R
import com.example.novinshop_project.base.BaseViewModel
import com.example.novinshop_project.base.singleObserver
import com.example.novinshop_project.data.EmptyState
import com.example.novinshop_project.data.ResponseFavourite
import com.example.novinshop_project.feature.profile.favourite.repo.FavouriteRepository
import com.example.novinshop_project.utils.DigiShopSingleObserver

class FavouriteViewModel(val repository: FavouriteRepository):BaseViewModel() {
    val favouriteLiveData = MutableLiveData<List<ResponseFavourite>>()
    val emptyStateLiveData = MutableLiveData<EmptyState>()

    init {
        getFavourite()
    }

    fun getFavourite()
    {
        progressBarLiveData.value = true
        repository.getFavourite().singleObserver()
            .doFinally { progressBarLiveData.value = false }
            .subscribe(object :DigiShopSingleObserver<List<ResponseFavourite>>(compositeDisposable)
            {
                override fun onSuccess(t: List<ResponseFavourite>) {
                    if (!t.isNullOrEmpty())
                    {
                        favouriteLiveData.value = t
                    }
                    else
                    {
                        emptyStateLiveData.value = EmptyState(true, R.string.emptyFavourite)
                    }

                }
            })
    }
}