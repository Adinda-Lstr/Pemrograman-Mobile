package com.example.scrollablelistcompose.core.local

import android.content.Context

class SharedPrefsHelper(context: Context) {
    private val prefs = context.getSharedPreferences("app_settings", Context.MODE_PRIVATE)

    fun setFirstTimeOpen(isFirstTime: Boolean) {
        prefs.edit().putBoolean("is_first_time", isFirstTime).apply()
    }

    fun isFirstTimeOpen(): Boolean {
        return prefs.getBoolean("is_first_time", true)
    }
}