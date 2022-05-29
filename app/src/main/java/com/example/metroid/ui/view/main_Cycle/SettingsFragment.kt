package com.example.metroid.ui.view.main_Cycle

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.metroid.databinding.FragmentSettingsBinding
import com.example.metroid.utils.SettingsItemData


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
        }

        fragmentSettingsBinding.cvDelete.setOnClickListener {

        }

        fragmentSettingsBinding


        return fragmentSettingsBinding.root
    }


}