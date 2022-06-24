package com.example.metroid.ui.view.settings_cycle

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.metroid.databinding.FragmentMetroTripHistoryBinding
import com.example.metroid.ui.view.viewmodel.main_cycle.MetroViewModel

class MetroTripHistoryFragment : Fragment() {

    private lateinit var binding: FragmentMetroTripHistoryBinding
    private lateinit var metroViewModel: MetroViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentMetroTripHistoryBinding.inflate(inflater)
        metroViewModel = ViewModelProvider(requireActivity())[MetroViewModel::class.java]


        return binding.root
    }
}