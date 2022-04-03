package com.example.weatherapp.weatherApi

import com.example.weatherapp.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "http://api.openweathermap.org/data/2.5/"

// Instance for api that uses openweathermap url
object WeatherRetrofitInstance {
    val api: WeatherApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient.Builder().addInterceptor {
                // Building url with params, because app uses only one endpoint
                // so there is no need to use them in WeatherApi
                // BuildConfig.OpenWeatherMapApiKey is an ApiKey, so it is not hardcoded
                // and added to .gitignore
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
