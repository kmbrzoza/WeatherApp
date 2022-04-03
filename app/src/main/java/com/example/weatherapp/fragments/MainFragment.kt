package com.example.weatherapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.weatherapp.R
import com.example.weatherapp.services.NavigatorService
import com.google.android.material.floatingactionbutton.FloatingActionButton

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

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            requireActivity().finish()
        }

        if (NavigatorService.isSeniorMode(activity)) {
            view.findViewById<Button>(R.id.searchButton).apply {
                setOnClickListener {
                    view.findNavController().navigate(R.id.action_mainFragment_to_searchFragment)
                }
            }
        } else {
            view.findViewById<FloatingActionButton>(R.id.fab).apply {
                setOnClickListener {
                    view.findNavController().navigate(R.id.action_mainFragment_to_searchFragment)
                }
            }
        }

        view.findViewById<Button>(R.id.settingsButton).apply {
            setOnClickListener {
                view.findNavController().navigate(R.id.action_mainFragment_to_preferencesFragment)
            }
        }
    }
}