package com.peakflow.you_must_not_cheat_yourself_your_parents_raised_a_soldier_prove_it

object Constants {
    // Firebase
    const val FIREBASE_DATABASE_USERS = "Users"
    
    // Shared Preferences
    const val PREF_NAME = "MyAppPrefs"
    const val PREF_UID = "uid"
    
    // Notification
    const val NOTIFICATION_CHANNEL_ID = "peakflow_channel"
    const val NOTIFICATION_CHANNEL_NAME = "Peakflow Notifications"
    
    // App Checker Service
    const val APP_CHECK_INTERVAL = 1000L
    val BLACKLISTED_PACKAGES = arrayOf(
        "com.instagram.android",
        "com.facebook.katana", 
        "com.google.android.youtube"
    )
    
    // API
    const val GEMINI_API_BASE_URL = "https://generativelanguage.googleapis.com/v1beta/models/gemini-pro:generateText"
    const val GEMINI_API_KEY = "YOUR_API_KEY" // Replace with actual API key
    
    // Time intervals
    const val SPLASH_DELAY = 2000L
    const val USAGE_STATS_INTERVAL = 10000L
    
    // Validation
    const val MIN_PASSWORD_LENGTH = 6
    const val MIN_AGE = 1
    const val MAX_AGE = 120
    
    // UI
    const val MAX_GOAL_LENGTH = 500
    const val MAX_DAILY_SLOTS = 18
    const val DAYS_IN_WEEK = 7
    
    // Analytics Events
    const val EVENT_GOAL_CREATED = "goal_created"
    const val EVENT_TASK_COMPLETED = "task_completed"
    const val EVENT_USER_REGISTRATION = "user_registration"
} 