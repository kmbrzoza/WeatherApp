package com.example.weatherapp.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Weather(
    val weather: List<WeatherDescription>,
    val main: WeatherMain,
    val sys: WeatherSys,
    val timezone: Long,
    val cod: Int,
    val name: String,
    val dt: Long
) : Parcelable

@Parcelize
data class WeatherDescription(
    val main: String,
    val description: String,
    val icon: String,
) : Parcelable

@Parcelize
data class WeatherMain(
    val temp: Float,
    val feels_like: Float,
    val temp_min: Float,
    val temp_max: Float,
    val pressure: Int,
    val humidity: Int,
) : Parcelable

@Parcelize
data class WeatherSys(
    val sunrise: Long,
    val sunset: Long,
) : Parcelable
