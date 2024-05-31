package com.example.ejpeeye

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment


class LandingPageFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_landing_page, container, false)

        val weatherBtn = view.findViewById<Button>(R.id.weather_btn)
        val dbzBtn = view.findViewById<Button>(R.id.dbz_btn)

        weatherBtn.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.landing_layout, WeatherFragment())
                .addToBackStack(null)
                .commit()
        }

        dbzBtn.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.landing_layout, DbzFragment())
                .addToBackStack(null)
                .commit()
        }

        return view
    }
}
