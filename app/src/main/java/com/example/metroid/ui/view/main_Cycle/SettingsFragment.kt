package com.example.metroid.ui.view.main_Cycle

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.metroid.R
import com.example.metroid.databinding.FragmentSettingsBinding
import com.example.metroid.ui.adapter.SettingsAdapter
import com.example.metroid.utils.SettingsItemData


class SettingsFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var fragmentSettingsBinding: FragmentSettingsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        arguments?.let {
//            param1 = it.getString(ARG_PARAM1)
//            param2 = it.getString(ARG_PARAM2)
//        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentSettingsBinding = FragmentSettingsBinding.inflate(inflater)
        val list = mutableListOf<SettingsItemData>()
        for (i in 1..10) {
            list.add(SettingsItemData("R.drawable.ic_person_blue", "settings"))

        }
        val adapter = SettingsAdapter(this,list)
        fragmentSettingsBinding.rvSettings.layoutManager = GridLayoutManager(requireContext(), 2)
        fragmentSettingsBinding.rvSettings.adapter = adapter

        return fragmentSettingsBinding.root
    }


}