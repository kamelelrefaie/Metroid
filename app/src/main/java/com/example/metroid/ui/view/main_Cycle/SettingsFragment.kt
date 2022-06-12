package com.example.metroid.ui.view.main_Cycle

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.metroid.databinding.FragmentSettingsBinding
import java.lang.Exception


class SettingsFragment : Fragment() {


    private lateinit var fragmentSettingsBinding: FragmentSettingsBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentSettingsBinding = FragmentSettingsBinding.inflate(inflater)

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

        return fragmentSettingsBinding.root
    }

    override fun onResume() {
        super.onResume()
        if (requireActivity() is HomeActivity) {
            (activity as HomeActivity).showBottomNavigationView()
        }
    }
}