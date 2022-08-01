package com.example.metroid.ui.view.main_Cycle

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.metroid.databinding.FragmentSettingsBinding
import com.example.metroid.ui.view.onboarding_cycle.OnBoardingActivity
import com.example.metroid.ui.view.viewmodel.login_cycle.LoginViewModel
import kotlinx.coroutines.launch
import java.lang.Exception


class SettingsFragment : Fragment() {


    private lateinit var fragmentSettingsBinding: FragmentSettingsBinding
    lateinit var mLoginViewModel: LoginViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentSettingsBinding = FragmentSettingsBinding.inflate(inflater)
        mLoginViewModel = ViewModelProvider(requireActivity())[LoginViewModel::class.java]

        //go to show ticket
        fragmentSettingsBinding.cvShow.setOnClickListener {
            findNavController().navigate(SettingsFragmentDirections.actionSettingsFragmentToShowTicketFragment2())
            if (requireActivity() is HomeActivity) {
                (activity as HomeActivity).hideBottomNavigationView()
            }
        }

        fragmentSettingsBinding.cvDelete.setOnClickListener {

            findNavController().navigate(SettingsFragmentDirections.actionSettingsFragmentToCancelTicketFragment())
            if (requireActivity() is HomeActivity) {
                (activity as HomeActivity).hideBottomNavigationView()
            }

        }

        fragmentSettingsBinding.cvFeedback.setOnClickListener {

            findNavController().navigate(SettingsFragmentDirections.actionSettingsFragmentToFeedbackFragment())
            if (requireActivity() is HomeActivity) {
                (activity as HomeActivity).hideBottomNavigationView()
            }

        }

        fragmentSettingsBinding.cvEditProfile.setOnClickListener {
            findNavController().navigate(SettingsFragmentDirections.actionSettingsFragmentToEditProfileFragment())
            if (requireActivity() is HomeActivity) {
                (activity as HomeActivity).hideBottomNavigationView()
            }
        }

        fragmentSettingsBinding.cvMetroTripsHistory.setOnClickListener {
            findNavController().navigate(SettingsFragmentDirections.actionSettingsFragmentToMetroTripHistoryFragment())
            if (requireActivity() is HomeActivity) {
                (activity as HomeActivity).hideBottomNavigationView()
            }
        }

        fragmentSettingsBinding.cvShowId.setOnClickListener {
            findNavController().navigate(SettingsFragmentDirections.actionSettingsFragmentToShowUserIDFragment())
            if (requireActivity() is HomeActivity) {
                (activity as HomeActivity).hideBottomNavigationView()
            }
        }


        //logout
        fragmentSettingsBinding.btnLogout.setOnClickListener {

            viewLifecycleOwner.lifecycleScope.launch {
                mLoginViewModel.saveNameAndUserIdToDataStore("",-1L,requireActivity())
                startActivity(Intent(requireActivity(), OnBoardingActivity::class.java))
                requireActivity().finish()
            }
        }
        return fragmentSettingsBinding.root
    }

    override fun onResume() {
        super.onResume()
        if (requireActivity() is HomeActivity) {
            (activity as HomeActivity).showBottomNavigationView()
        }
    }
}