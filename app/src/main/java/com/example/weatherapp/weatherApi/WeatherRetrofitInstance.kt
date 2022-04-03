package com.example.weatherapp.weatherApi

import com.example.weatherapp.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "http://api.openweathermap.org/data/2.5/"

object WeatherRetrofitInstance {
    val api: WeatherApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient.Builder().addInterceptor {
                val url = it.request()
                    .url()
                    .newBuilder()
                    .addPathSegment("weather")
                    .addQueryParameter("appid", BuildConfig.OpenWeatherMapApiKey)
                    .addQueryParameter("lang", "en")
                    .addQueryParameter("units", "metric")
                    .build()
                it.proceed(
                    it.request()
                        .newBuilder()
                        .url(url)
                        .build()
                )
            }.build())
            .build()
            .create(WeatherApi::class.java)
    }
}
