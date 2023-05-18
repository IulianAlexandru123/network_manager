package com.example.networkmanager.ui.home

import android.net.wifi.ScanResult
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {

    private val _wifiResult = MutableLiveData<List<ScanResult>>()
    val wifiResult: LiveData<List<ScanResult>> = _wifiResult

    fun setWifiResults(result: List<ScanResult>) {
        _wifiResult.value = result
    }
}