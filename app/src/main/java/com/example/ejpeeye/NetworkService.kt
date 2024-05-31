package com.example.ejpeeye

import android.content.Context
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class NetworkService(context: Context) {
    private val requestQueue: RequestQueue = Volley.newRequestQueue(context.applicationContext)

    fun getJsonObject(url: String, successCallback: (JSONObject) -> Unit, errorCallback: (Throwable) -> Unit) {
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            { response ->
                successCallback(response)
            },
            { error ->
                errorCallback(error)
            }
        )
        requestQueue.add(jsonObjectRequest)
    }
}
