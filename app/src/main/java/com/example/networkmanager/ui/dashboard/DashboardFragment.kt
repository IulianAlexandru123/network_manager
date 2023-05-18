package com.example.networkmanager.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import com.example.networkmanager.databinding.FragmentDashboardBinding

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private val dashboardViewModel: DashboardViewModel by activityViewModels()
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        performInitialServerRequest()
        initObservers()
        binding.updateButton.setOnClickListener {
            dashboardViewModel.updateCurrentValue(
                dashboardViewModel.text.value?.toInt()?.inc().toString()
            )
        }
    }

    private fun performInitialServerRequest() {
        dashboardViewModel.getCurrentValue()
    }

    private fun initObservers() {
        dashboardViewModel.text.observe(viewLifecycleOwner) {
            binding.textDashboard.text = "Current Value is: $it"
        }

        dashboardViewModel.loading.observe(viewLifecycleOwner) {
            binding.loadingIndicator.isVisible = it
        }
    }
}