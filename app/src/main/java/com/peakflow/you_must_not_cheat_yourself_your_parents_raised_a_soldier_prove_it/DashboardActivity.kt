package com.peakflow.you_must_not_cheat_yourself_your_parents_raised_a_soldier_prove_it

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.SeekBar
import android.widget.Button
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import android.graphics.Color
import android.app.AppOpsManager
import android.content.Context
import android.os.Process
import android.provider.Settings
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardActivity : AppCompatActivity() {
    private val viewModel: DashboardViewModel by viewModels()
    private lateinit var suggestedGoalText: TextView
    private lateinit var goalTimeText: TextView
    private lateinit var progressSeekBar: SeekBar
    private lateinit var timelineSeekBar: SeekBar
    private lateinit var performanceChart: LineChart
    private lateinit var viewDetailsBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        suggestedGoalText = findViewById(R.id.suggested_goal_output)
        goalTimeText = findViewById(R.id.textView20)
        progressSeekBar = findViewById(R.id.seekBar)
        timelineSeekBar = findViewById(R.id.seekBar2)
        performanceChart = findViewById(R.id.performanceChart)
        viewDetailsBtn = findViewById(R.id.button5)

        viewModel.userData.observe(this) { user ->
            user?.let {
                updateUI(it)
            }
        }

        viewDetailsBtn.setOnClickListener {
            startActivity(Intent(this, PerformanceChartActivity::class.java))
        }

        setupPerformanceChart()
        
        checkUsageStatsPermission()
    }

    private fun checkUsageStatsPermission() {
        val appOps = getSystemService(Context.APP_OPS_SERVICE) as AppOpsManager
        val mode = appOps.checkOpNoThrow(
            AppOpsManager.OPSTR_GET_USAGE_STATS,
            Process.myUid(),
            packageName
        )
        if (mode != AppOpsManager.MODE_ALLOWED) {
            startActivity(Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS))
        } else {
            startService(Intent(this, AppCheckerService::class.java))
        }
    }

    private fun updateUI(user: User) {
        suggestedGoalText.text = user.final_goal ?: "Set your goals!"
        goalTimeText.text = user.deadline ?: "N/A"
        
        // Example progress calculation
        progressSeekBar.progress = 65 // Placeholder
        timelineSeekBar.progress = 40 // Placeholder
    }

    private fun setupPerformanceChart() {
        val entries = arrayListOf(
            Entry(0f, 2f),
            Entry(1f, 4f),
            Entry(2f, 3f),
            Entry(3f, 5f),
            Entry(4f, 4f)
        )
        val dataSet = LineDataSet(entries, "Performance").apply {
            color = Color.GREEN
            setCircleColor(Color.GREEN)
            lineWidth = 2f
            setDrawFilled(true)
            fillColor = Color.GREEN
            alpha = 50
        }
        performanceChart.data = LineData(dataSet)
        performanceChart.description.isEnabled = false
        performanceChart.xAxis.setDrawGridLines(false)
        performanceChart.axisLeft.setDrawGridLines(false)
        performanceChart.axisRight.isEnabled = false
        performanceChart.invalidate()
    }
} 