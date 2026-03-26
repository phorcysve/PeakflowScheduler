package com.peakflow.you_must_not_cheat_yourself_your_parents_raised_a_soldier_prove_it

import android.app.Service
import android.app.usage.UsageStats
import android.app.usage.UsageStatsManager
import android.content.Context
import android.content.Intent
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import android.util.Log

class AppCheckerService : Service() {
    companion object {
        private const val TAG = "AppCheckerService"
        private const val CHECK_INTERVAL = 1000L // 1 second interval
    }

    private val blacklistedPackages = arrayOf(
        "com.instagram.android",
        "com.facebook.katana",
        "com.google.android.youtube"
    )

    private val handler = Handler(Looper.getMainLooper())
    private val runnable = object : Runnable {
        override fun run() {
            val foregroundApp = getForegroundApp()
            Log.d(TAG, "Foreground app: $foregroundApp")

            blacklistedPackages.forEach { pkg ->
                if (foregroundApp == pkg) {
                    Log.d(TAG, "Blocked app detected: $pkg")
                    launchBlockerOverlay()
                    return@forEach
                }
            }
            handler.postDelayed(this, CHECK_INTERVAL)
        }
    }

    override fun onCreate() {
        super.onCreate()
        handler.post(runnable)
    }

    private fun launchBlockerOverlay() {
        val intent = Intent(this, BlockerActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        }
        startActivity(intent)
    }

    private fun getForegroundApp(): String? {
        val currentTime = System.currentTimeMillis()
        val usm = getSystemService(Context.USAGE_STATS_SERVICE) as UsageStatsManager

        val usageStatsList = usm.queryUsageStats(
            UsageStatsManager.INTERVAL_DAILY,
            currentTime - 10000,
            currentTime
        )

        if (usageStatsList.isNullOrEmpty()) {
            Log.d(TAG, "No usage stats available. Ensure Usage Access is granted.")
            return null
        }

        return usageStatsList.maxByOrNull { it.lastTimeUsed }?.packageName
    }

    override fun onBind(intent: Intent): IBinder? = null

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacks(runnable)
    }
} 