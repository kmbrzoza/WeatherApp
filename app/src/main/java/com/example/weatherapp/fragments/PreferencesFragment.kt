package com.example.weatherapp.fragments

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.example.weatherapp.R

class PreferencesFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences, rootKey)
    }
}