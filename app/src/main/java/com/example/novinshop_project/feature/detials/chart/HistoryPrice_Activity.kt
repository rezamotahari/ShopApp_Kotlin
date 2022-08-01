package com.example.novinshop_project.feature.detials.chart

import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import com.example.novinshop_project.R
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import kotlinx.android.synthetic.main.activity_history_price.*
import kotlinx.android.synthetic.main.layout_by_product.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import timber.log.Timber

val dates = mutableListOf<String>()

class HistoryPrice_Activity : AppCompatActivity() {
    val historyViewMolde: HistoryViewMolde by viewModel {
        parametersOf(
            intent.getIntExtra(
                "id",
                0
            )
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        setContentView(R.layout.activity_history_price)

        img_Back.setOnClickListener {
            finish()
        }
        val typeFace: Typeface = Typeface.createFromAsset(assets, "vazir_medium.ttf")

        val legend: Legend = line_Chart.legend
        legend.typeface = typeFace
        val leftAxis: YAxis = line_Chart.axisLeft
        leftAxis.typeface = typeFace
        line_Chart.axisRight.isEnabled = false
        line_Chart.description.isEnabled = false

        val xAxis = line_Chart.xAxis
        xAxis.isEnabled = true
        xAxis.typeface = typeFace


        historyViewMolde.historyChartLiveData.observe(this)
        {
            Timber.i("$it")
            if (it.isNotEmpty()) {
                var values: MutableList<Entry> = ArrayList()
                for (i in it.indices) {
                    values.add(Entry(i.toFloat(), it[i].price.toFloat()))
                    dates.add(it[i].date)
                }

                val lineDataSet = LineDataSet(values, "نمودار قیمت").apply {
                    valueTypeface = typeFace
                    color = ContextCompat.getColor(this@HistoryPrice_Activity,R.color.green_500)
                    lineWidth = 2f
                    valueTextSize = 10f
                    valueTextColor = ContextCompat.getColor(this@HistoryPrice_Activity,R.color.gray_700)
                    setCircleColor(ContextCompat.getColor(this@HistoryPrice_Activity,R.color.yellow))
                    setDrawFilled(true)

                    fillDrawable= ContextCompat.getDrawable(this@HistoryPrice_Activity,R.drawable.shape_chart)
                }

                val iLineDataSet: MutableList<ILineDataSet> = ArrayList()
                iLineDataSet.add(lineDataSet)
                val lineData = LineData(iLineDataSet)
                line_Chart.data = lineData
                val xAxis = line_Chart.xAxis
                xAxis.labelCount = 4
                xAxis.valueFormatter = MyXaxis()
                line_Chart.animateX(500)
                line_Chart.invalidate()
            }
        }

    }
}

class MyXaxis : ValueFormatter() {
    override fun getAxisLabel(value: Float, axis: AxisBase?): String {
        return dates.getOrNull(value.toInt()) ?: value.toString()
    }
}