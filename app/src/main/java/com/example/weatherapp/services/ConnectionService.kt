package com.example.weatherapp.services

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

// Service that checks if there is network connection
class ConnectionService {
    companion object {
        fun isNetworkConnected(context: Context): Boolean {
            val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
            return activeNetwork?.isConnected == true
        }
    }
}