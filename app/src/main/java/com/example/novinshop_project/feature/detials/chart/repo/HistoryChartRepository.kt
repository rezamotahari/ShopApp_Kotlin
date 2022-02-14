package com.example.novinshop_project.feature.detials.chart.repo

import com.example.novinshop_project.data.ResponseHistoryPrice
import com.example.novinshop_project.feature.detials.adapter.AdapterSizeProduct
import io.reactivex.Single

interface HistoryChartRepository {
    fun getHistoryChart(productid :Int):Single<List<ResponseHistoryPrice>>
}