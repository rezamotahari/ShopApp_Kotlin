package com.example.novinshop_project.feature.detials.chart.source

import com.example.novinshop_project.data.ResponseHistoryPrice
import com.example.novinshop_project.feature.detials.adapter.AdapterSizeProduct
import io.reactivex.Single

interface HistoryChartDataSource {
    fun getHistoryChart(productid :Int):Single<List<ResponseHistoryPrice>>
}