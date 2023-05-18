package com.example.networkmanager.ui.home

import android.net.wifi.ScanResult
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.networkmanager.databinding.ItemWifiBinding


class WifiAdapter : RecyclerView.Adapter<WifiAdapter.ItemReservationViewHolder>() {

    private var wifiList: List<ScanResult> = listOf()

    var callBack: ((Event) -> Unit)? = null

    fun setItems(wifis: List<ScanResult>) {
        this.wifiList = wifis
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): WifiAdapter.ItemReservationViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        return ItemReservationViewHolder(ItemWifiBinding.inflate(inflater, parent, false))
    }

    override fun getItemCount() = this.wifiList.size

    override fun onBindViewHolder(holder: ItemReservationViewHolder, position: Int) {
        val wifi = wifiList[position]

        holder.apply {
            wifiName.text = wifi.SSID
            frequency.text = "Frequency: ${wifi.frequency}"

            moreInfoButton.apply {
                setOnClickListener {
                    callBack?.invoke(Event(wifi))
                }
            }
        }
    }

    inner class ItemReservationViewHolder(binding: ItemWifiBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val wifiName = binding.wifiNameTextView
        val frequency = binding.frequencyTextView
        val moreInfoButton = binding.moreInfoButton
    }

    data class Event(val wifi: ScanResult)
}