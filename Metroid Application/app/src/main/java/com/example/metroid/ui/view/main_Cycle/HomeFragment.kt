package com.example.metroid.ui.view.main_Cycle

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.metroid.R
import com.example.metroid.databinding.FragmentHomeBinding
import com.example.metroid.ui.view.viewmodel.main_cycle.MetroViewModel
import java.lang.Exception


class HomeFragment : Fragment() {

    private lateinit var fragmentHomeBinding: FragmentHomeBinding

    private lateinit var metroViewModel: MetroViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentHomeBinding = FragmentHomeBinding.inflate(layoutInflater)
        metroViewModel = ViewModelProvider(requireActivity())[MetroViewModel::class.java]
        metroViewModel.getTripCreditsAndCount(requireActivity())
        metroViewModel.credits.observe(requireActivity()) {
            fragmentHomeBinding.tvAmount.text = "$it L.E"
        }

        fragmentHomeBinding.tilSearch.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToSearchFragment())
        }
        fragmentHomeBinding.ivNfc.let {
            Glide.with(requireActivity())
                .load(R.drawable.ic_nfc).circleCrop().into(it)
        }

        try {
            fragmentHomeBinding.ivNfc.setOnClickListener {
                startActivity(Intent(requireActivity(), NfcPageActivity::class.java))
            }
        } catch (e: Exception) {

        }


        fragmentHomeBinding.ivProfilePic.let {
            Glide.with(requireActivity()).load(R.drawable.ic_person).circleCrop().into(it)
        }

        return fragmentHomeBinding.root
    }


}