package com.example.metroid.ui.view.settings_cycle

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.metroid.databinding.FragmentEditProfileBinding
import com.example.metroid.ui.view.viewmodel.main_cycle.MetroViewModel
import com.example.metroid.ui.view.viewmodel.main_cycle.ReservationViewModel

class EditProfileFragment: Fragment(){

    private lateinit var editProfileBinding: FragmentEditProfileBinding
    private lateinit var metroViewModel: MetroViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        editProfileBinding= FragmentEditProfileBinding.inflate(inflater)
        metroViewModel = ViewModelProvider(requireActivity())[MetroViewModel::class.java]
        try {
            metroViewModel.getTripCreditsAndCount(requireActivity())
            editProfileBinding.tvCredit.text=metroViewModel.credits.value + "$"
            editProfileBinding.tvTrip.text=metroViewModel.trips.value
        }catch (e:Exception){

        }
        return editProfileBinding.root
    }
}