package com.example.novinshop_project.feature.detials.chart.source

import com.example.novinshop_project.api.ApiService
import com.example.novinshop_project.data.ResponseHistoryPrice
import io.reactivex.Single

class RmoteHistoryChartDataSource(val apiService: ApiService):HistoryChartDataSource {
    override fun getHistoryChart(productid: Int): Single<List<ResponseHistoryPrice>>  = apiService.getHistoryChart(productid)
}