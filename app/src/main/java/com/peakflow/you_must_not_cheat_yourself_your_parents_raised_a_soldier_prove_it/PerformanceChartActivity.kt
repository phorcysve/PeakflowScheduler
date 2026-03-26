package com.peakflow.you_must_not_cheat_yourself_your_parents_raised_a_soldier_prove_it

import android.graphics.Color
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import androidx.lifecycle.ViewModel

@AndroidEntryPoint
class PerformanceChartActivity : AppCompatActivity() {
    private val viewModel: PerformanceChartViewModel by viewModels()
    private lateinit var lineChart: LineChart
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_your_performance)
        lineChart = findViewById(R.id.lineChart)
        val entries = arrayListOf(
            Entry(0f, 2f),
            Entry(1f, 4f),
            Entry(2f, 6f),
            Entry(3f, 8f),
            Entry(4f, 10f)
        )
        val dataSet = LineDataSet(entries, "Sales Data").apply {
            color = Color.BLUE
            valueTextColor = Color.BLACK
            lineWidth = 2f
            circleRadius = 5f
            setCircleColor(Color.RED)
            setDrawCircleHole(false)
            setDrawFilled(true)
            fillColor = Color.CYAN
        }
        val lineData = LineData(dataSet)
        lineChart.data = lineData
        lineChart.invalidate()
        val xAxis: XAxis = lineChart.xAxis
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.granularity = 1f
        val leftAxis: YAxis = lineChart.axisLeft
        leftAxis.granularity = 1f
        val rightAxis: YAxis = lineChart.axisRight
        rightAxis.isEnabled = false
    }
}

class PerformanceChartViewModel @Inject constructor() : ViewModel() {
    // Add LiveData and business logic here if needed
} 