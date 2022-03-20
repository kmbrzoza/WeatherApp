package com.example.weatherapp.weatherApi

import com.example.weatherapp.model.Weather
import retrofit2.awaitResponse

class WeatherRepository {
    companion object {
        suspend fun getCityWeatherInfo(cityName: String): Weather? {
            return WeatherRetrofitInstance.api.getCityWeatherInfo(cityName).awaitResponse().body()
        }
    }
}