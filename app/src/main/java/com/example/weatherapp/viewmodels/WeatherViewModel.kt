package com.example.weatherapp.viewmodels

import android.app.Application
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import java.net.URL
import java.text.SimpleDateFormat
import java.util.*

class WeatherViewModel(application: Application) : AndroidViewModel(application) {
    private val _bitmapWeatherImage: MutableLiveData<Bitmap> = MutableLiveData()
    val bitmapWeatherImage: LiveData<Bitmap>
        get() {
            return _bitmapWeatherImage
        }

    // Gets wheater bitmap image that depends on iconId using openweathermap url
    fun getWeatherBitmapImage(iconId: String) {
        val url = getWeatherImageUrl(iconId)
        val thread = Thread {
            try {
                val inputStream = URL(url).openStream()
                val bitmap = BitmapFactory.decodeStream(inputStream)
                _bitmapWeatherImage.postValue(bitmap)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        thread.start()
    }

    // formats date based on timespan
    fun getFormattedDateTimeBasedOnTimeStamp(timeSpanSeconds: Long): String {
        return getFormattedDate(getDateBasedOnTimeStamp(timeSpanSeconds))
    }

    // formats time based on timespan
    fun getFormattedTimeBasedOnTimeStamp(timeSpanSeconds: Long): String {
        return getFormattedTime(getDateBasedOnTimeStamp(timeSpanSeconds))
    }

    // gets date based on timespan
    private fun getDateBasedOnTimeStamp(timeSpanSeconds: Long): Date {
        return Date(timeSpanSeconds * 1000)
    }

    private fun getFormattedDate(date: Date): String {
        val dateFormat = SimpleDateFormat("HH:mm dd.MM.yyyy")
        return dateFormat.format(date)
    }

    private fun getFormattedTime(date: Date): String {
        val dateFormat = SimpleDateFormat("HH:mm")
        return dateFormat.format(date)
    }

    private fun getWeatherImageUrl(iconId: String): String {
        return "http://openweathermap.org/img/wn/${iconId}@2x.png"
    }
}