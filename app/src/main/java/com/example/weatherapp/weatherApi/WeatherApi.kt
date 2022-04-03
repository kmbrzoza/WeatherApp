package com.example.weatherapp.weatherApi

import com.example.weatherapp.model.Weather
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WeatherApi {
    // endpoint for weather info. Using '.' because in url there are already query params
    @GET(".")
    fun getCityWeatherInfo(@Query("q") cityName: String): Call<Weather>
}