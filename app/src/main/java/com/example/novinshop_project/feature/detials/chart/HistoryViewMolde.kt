package com.example.novinshop_project.feature.detials.chart

import androidx.lifecycle.MutableLiveData
import com.example.novinshop_project.base.BaseViewModel
import com.example.novinshop_project.base.singleObserver
import com.example.novinshop_project.data.ResponseDetailsProduct
import com.example.novinshop_project.data.ResponseHistoryPrice
import com.example.novinshop_project.feature.detials.chart.repo.HistoryChartRepository
import com.example.novinshop_project.utils.DigiShopSingleObserver

class HistoryViewMolde(val productId:Int,val historyChartRepository: HistoryChartRepository):BaseViewModel() {


    val historyChartLiveData = MutableLiveData<List<ResponseHistoryPrice>>()
    val productIdLiveData = MutableLiveData<Int>()

    init {
        productIdLiveData.value = productId
        progressBarLiveData.value = true
        historyChartRepository.getHistoryChart(productIdLiveData.value!!).singleObserver()
            .doFinally {

                progressBarLiveData.value = false
            }
            .subscribe(object :DigiShopSingleObserver<List<ResponseHistoryPrice>>(compositeDisposable)
            {
                override fun onSuccess(t: List<ResponseHistoryPrice>) {

                    historyChartLiveData.value = t
                }

            })

    }
}