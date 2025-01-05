package com.ranxom.xpense.data.local

import android.content.Context
import android.content.SharedPreferences

class OnBoardingManager(context: Context) {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("onboarding_preferences", Context.MODE_PRIVATE)

    companion object {
        const val ONBOARDING_COMPLETED_KEY = "onboarding_completed"
    }

    fun isOnboardingCompleted(): Boolean {
        return sharedPreferences.getBoolean(ONBOARDING_COMPLETED_KEY, false)
    }

    fun setOnboardingCompleted(isCompleted: Boolean) {
        sharedPreferences.edit().putBoolean(ONBOARDING_COMPLETED_KEY, isCompleted).apply()
    }
}