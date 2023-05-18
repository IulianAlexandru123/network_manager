package com.example.networkmanager.ui.dashboard

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import okhttp3.Call
import okhttp3.Callback
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.Response
import java.io.IOException


class DashboardViewModel : ViewModel() {

    private val url = "http://10.0.2.2:8080/currentValue"
    val JSON = "application/json; charset=utf-8".toMediaTypeOrNull()

    private val _text = MutableLiveData<String>().apply {
        value = "Loading..."
    }
    val text: LiveData<String> = _text


    private val _loading = MutableLiveData<Boolean>().apply { value = false }
    val loading: LiveData<Boolean> = _loading


    fun getCurrentValue() {
        _loading.postValue(true)
        val client = OkHttpClient()
        val request: Request = Request.Builder()
            .url(url)
            .build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                call.cancel()
                _loading.postValue(false)
            }

            override fun onResponse(call: Call, response: Response) {
                _text.postValue(response.body?.string())
                _loading.postValue(false)
            }
        })
    }


    fun updateCurrentValue(updatedValue: String) {
        _loading.postValue(true)
        val client = OkHttpClient()

        val body: RequestBody = RequestBody.create(JSON, updatedValue)

        val request: Request = Request.Builder()
            .url(url)
            .post(body)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                call.cancel()
                _loading.postValue(false)
            }

            override fun onResponse(call: Call, response: Response) {
                _text.postValue(response.body?.string())
                _loading.postValue(false)
            }
        })
    }
}