package com.example.weatherapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.weatherapp.R
import com.example.weatherapp.model.Weather
import com.example.weatherapp.services.NavigatorService
import com.example.weatherapp.viewmodels.WeatherViewModel
import java.util.*

class WeatherFragment : Fragment() {
    private lateinit var viewModel: WeatherViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(NavigatorService.weatherLayoutId(activity), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val weather = arguments?.get("weather") as Weather?

        if (weather == null) {
            Toast.makeText(activity, "Weather not found!", Toast.LENGTH_LONG).show()
            backToSearchFragment(view)
            return
        }

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            backToSearchFragment(view)
        }

        viewModel = WeatherViewModel((requireNotNull(this.activity).application))

        view.findViewById<TextView>(R.id.weather_main_title).text = weather.name

        val date = viewModel.getFormattedDateTimeBasedOnTimeStamp(weather.dt + weather.timezone)
        view.findViewById<TextView>(R.id.weather_main_content).text = date

        val temperature = "${weather.main.temp} °C"
        view.findViewById<TextView>(R.id.weather_temperature_content).text = temperature

        val pressure = "${weather.main.pressure} hPa"
        view.findViewById<TextView>(R.id.weather_pressure_content).text = pressure

        val sunrise =
            viewModel.getFormattedTimeBasedOnTimeStamp(weather.sys.sunrise + weather.timezone)
        view.findViewById<TextView>(R.id.weather_sunrise_content).text = sunrise

        val sunset =
            viewModel.getFormattedTimeBasedOnTimeStamp(weather.sys.sunset + weather.timezone)
        view.findViewById<TextView>(R.id.weather_sunset_content).text = sunset

        view.findViewById<TextView>(R.id.weather_description_content).text =
            weather.weather.first().description.replaceFirstChar {
                if (it.isLowerCase()) it.titlecase(
                    Locale.getDefault()
                ) else it.toString()
            }

        viewModel.bitmapWeatherImage.observe(viewLifecycleOwner) {
            view.findViewById<ImageView>(R.id.weather_description_image).setImageBitmap(it)
        }

        viewModel.getWeatherBitmapImage(weather.weather.first().icon)
    }

    private fun backToSearchFragment(view: View) {
        view.findNavController().navigate(R.id.action_weatherFragment_to_searchFragment)
    }
}