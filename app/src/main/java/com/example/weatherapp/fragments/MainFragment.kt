package com.example.weatherapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.addCallback
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.weatherapp.R
import com.example.weatherapp.services.ConnectionService
import com.example.weatherapp.services.NavigatorService

class MainFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(NavigatorService.mainLayoutId(activity), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // If user click back (android button) then close app
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            requireActivity().finish()
        }

        // Button settings
        view.findViewById<Button>(R.id.button_settings).apply {
            setOnClickListener {
                view.findNavController().navigate(R.id.action_mainFragment_to_preferencesFragment)
            }
        }

        // Button search
        view.findViewById<Button>(R.id.button_search).apply {
            setOnClickListener {
                // If there is no internet connection then show toast
                if (!ConnectionService.isNetworkConnected(context)) {
                    Toast.makeText(
                        context,
                        "You need an internet connection",
                        Toast.LENGTH_LONG
                    ).show()
                } else {
                    // If there is internet connection then get value from textview and go to loading fragment
                    val cityName =
                        view.findViewById<EditText>(R.id.et_city_name).text.toString().trim()

                    if (cityName.isNotEmpty()) {
                        val bundle = bundleOf(Pair("cityName", cityName))
                        view.findNavController()
                            .navigate(R.id.action_mainFragment_to_loadingFragment, bundle)
                    } else {
                        Toast.makeText(
                            context,
                            "City name cannot be empty!",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }
}