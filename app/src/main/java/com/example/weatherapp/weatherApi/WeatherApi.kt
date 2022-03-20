package com.example.weatherapp.weatherApi

import com.example.weatherapp.model.Weather
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WeatherApi {

    @GET(".")
    fun getCityWeatherInfo(@Query("q") cityName: String): Call<Weather>
}