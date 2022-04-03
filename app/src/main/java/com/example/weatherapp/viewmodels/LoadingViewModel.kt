package com.example.weatherapp.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.model.Weather
import com.example.weatherapp.weatherApi.WeatherRepository
import kotlinx.coroutines.launch

class LoadingViewModel(application: Application) : AndroidViewModel(application) {
    private val _cityWeatherInfo: MutableLiveData<Weather?> = MutableLiveData()
    val cityWeatherInfo: LiveData<Weather?>
        get() {
            return _cityWeatherInfo
        }

    fun getCityWeatherInfo(cityName: String) {
        viewModelScope.launch {
            val weatherInfo = WeatherRepository.getCityWeatherInfo(cityName)
            _cityWeatherInfo.value = weatherInfo
        }
    }
}