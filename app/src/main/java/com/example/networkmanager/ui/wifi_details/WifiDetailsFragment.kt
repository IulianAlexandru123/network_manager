package com.example.networkmanager.ui.wifi_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.networkmanager.databinding.FragmentWifiDetailsBinding

class WifiDetailsFragment : Fragment() {

    private var _binding: FragmentWifiDetailsBinding? = null
    private val args: WifiDetailsFragmentArgs by navArgs()

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWifiDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initToolbar()
        initViews()
    }

    private fun initToolbar() {

    }

    private fun initViews() {
        val wifi = args.wifi

        binding.wifiNameTextView.text = "Name ${wifi.SSID}"
        binding.frequencyTextView.text = "Frequency ${wifi.frequency}"
        binding.capabilitiesTextView.text = "Capabilities ${wifi.capabilities}"
        binding.macAddressTextView.text = "MAC Address: ${wifi.apMldMacAddress}"
        binding.channelWidthTextView.text = "Channel Width ${wifi.channelWidth}"
        binding.BSSIDTextView.text = "Information Elements ${wifi.BSSID}"
    }
}