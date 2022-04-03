package com.example.weatherapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.weatherapp.R
import com.example.weatherapp.viewmodels.LoadingViewModel

class LoadingFragment : Fragment() {
    private lateinit var viewModel: LoadingViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_loading, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Get city name from arguments
        val cityName = arguments?.getString("cityName")

        if (cityName == null) {
            Toast.makeText(activity, "City name is missing!", Toast.LENGTH_LONG).show()
            backToMainFragment(view)
            return
        }

        // If user click back (android button) then return to main fragment
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            backToMainFragment(view)
        }

        // Creating viewmodel
        viewModel = LoadingViewModel((requireNotNull(this.activity).application))

        viewModel.cityWeatherInfo.observe(viewLifecycleOwner) {
            // if city not found then show toast and back to menu
            if (it == null) {
                Toast.makeText(activity, "City not found!", Toast.LENGTH_LONG).show()
                backToMainFragment(view)
            } else {
                // if city found then go to weather fragment
                val bundle = bundleOf(Pair("weather", it))
                view.findNavController()
                    .navigate(R.id.action_loadingFragment_to_weatherFragment, bundle)
            }
        }

        viewModel.getCityWeatherInfo(cityName)
    }

    private fun backToMainFragment(view: View) {
        view.findNavController().navigate(R.id.action_loadingFragment_to_mainFragment)
    }
}