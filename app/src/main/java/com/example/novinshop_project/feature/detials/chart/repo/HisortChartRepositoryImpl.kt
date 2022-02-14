package com.example.novinshop_project.feature.detials.chart.repo

import com.example.novinshop_project.data.ResponseHistoryPrice
import com.example.novinshop_project.feature.detials.chart.source.HistoryChartDataSource
import com.example.novinshop_project.feature.detials.chart.source.RmoteHistoryChartDataSource
import io.reactivex.Single

class HisortChartRepositoryImpl(val rmoteHistoryChartDataSource:HistoryChartDataSource):HistoryChartRepository {
    override fun getHistoryChart(productid: Int): Single<List<ResponseHistoryPrice>>  = rmoteHistoryChartDataSource.getHistoryChart(productid)
}