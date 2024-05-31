package com.example.ejpeeye



import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

import android.widget.TextView
import androidx.fragment.app.Fragment

import org.json.JSONObject


class DbzFragment : Fragment() {

    private lateinit var name: TextView
    private lateinit var race: TextView
    private lateinit var maxKi: TextView

    private lateinit var anotherOneBtn: Button
    private lateinit var networkService: NetworkService
    private var url = "https://dragonball-api.com/api/characters/" //58 max

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_dbz, container, false)
        name = view.findViewById(R.id.character_name)
        race = view.findViewById(R.id.character_race)
        maxKi = view.findViewById(R.id.character_maxKi)

        anotherOneBtn = view.findViewById(R.id.another_one_btn)
        networkService = NetworkService(requireContext())



        anotherOneBtn.setOnClickListener {
            fetchRandomCharacter(name,  race, maxKi)
        }
        fetchRandomCharacter(name,  race, maxKi)
        return view
    }

    private fun fetchRandomCharacter(name: TextView,race: TextView, maxKi: TextView) {
        val randomCharacterId = (1..58).random()
        val randomCharacterUrl = "$url$randomCharacterId"

        networkService.getJsonObject(randomCharacterUrl,
            { response ->
                handleCharacterResponse(response, name, race, maxKi)
            },
            { error ->
                Log.d("GET exception", error.toString())
            }
        )
    }

    private fun handleCharacterResponse(response: JSONObject, name: TextView, race: TextView, maxKi: TextView) {
        name.text = response.getString("name")
        race.text = getString(R.string.character_race_text, response.getString("race"))
        maxKi.text = getString(R.string.character_maxKi_text, response.getString("ki"))

    }
}
