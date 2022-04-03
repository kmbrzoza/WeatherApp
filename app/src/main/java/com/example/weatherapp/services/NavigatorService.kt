package com.example.weatherapp.services

import android.app.Activity
import androidx.preference.PreferenceManager
import com.example.weatherapp.R

private const val seniorModeKey = "seniorMode"

class NavigatorService {
    companion object {
        fun mainLayoutId(activity: Activity?): Int {
            return if (isSeniorMode(activity)) {
                R.layout.fragment_senior_main
            } else {
                R.layout.fragment_main
            }
        }

        fun searchLayoutId(activity: Activity?): Int {
            return if (isSeniorMode(activity)) {
                R.layout.fragment_senior_search
            } else {
                R.layout.fragment_search
            }
        }

        fun weatherLayoutId(activity: Activity?): Int {
            return if (isSeniorMode(activity)) {
                R.layout.fragment_senior_weather
            } else {
                R.layout.fragment_weather
            }
        }

        fun isSeniorMode(activity: Activity?): Boolean {
            if (activity == null) {
                return false
            }
            val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(activity)
            return sharedPreferences.getBoolean(seniorModeKey, false)
        }
    }
}