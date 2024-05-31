package com.example.ejpeeye

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController


class NavigatorFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_navigator, container, false)
        val logoutButton: Button = view.findViewById(R.id.button_logout)
        val backButton: Button = view.findViewById(R.id.back_btn)

        backButton.setOnClickListener {
            requireActivity().onBackPressed()
        }

        logoutButton.setOnClickListener {
            findNavController().popBackStack(R.id.login_layout, false)
            Log.d("Nav", "Logout pressed");
        }

        return view
    }
}
