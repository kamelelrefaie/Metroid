package com.example.metroid.ui.view.settings_cycle

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.metroid.databinding.FragmentShowUserIdBinding
import com.example.metroid.ui.view.viewmodel.settings_cycle.SettingsViewModel
import kotlinx.coroutines.launch

class ShowUserIDFragment : Fragment() {
    private lateinit var fragmentShowUserIdBinding: FragmentShowUserIdBinding
    private lateinit var mSettingsViewModel: SettingsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentShowUserIdBinding = FragmentShowUserIdBinding.inflate(inflater)
        mSettingsViewModel =
            ViewModelProvider(requireActivity())[SettingsViewModel::class.java]

        viewLifecycleOwner.lifecycleScope.launch {
            val prefData = mSettingsViewModel.getPrefData(requireActivity())
            fragmentShowUserIdBinding.tvCredit.text = "${prefData.id}"
        }
        return fragmentShowUserIdBinding.root
    }
}