package com.example.metroid.ui.view.onboarding_cycle.screen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.metroid.R
import com.example.metroid.databinding.FragmentOnBoardingViewPagerBinding
import com.example.metroid.databinding.FragmentThirdScreenBinding
import com.example.metroid.ui.view.onboarding_cycle.OnBoardingViewPagerFragmentDirections


class ThirdScreen : Fragment() {

    private var fragmentThirdScreenBinding: FragmentThirdScreenBinding? = null

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
        // Inflate the layout for this fragment
        fragmentThirdScreenBinding =
            FragmentThirdScreenBinding.inflate(layoutInflater)

        fragmentThirdScreenBinding?.next?.setOnClickListener {
            findNavController().navigate(OnBoardingViewPagerFragmentDirections.actionOnBoardingViewPagerFragmentToLoginFragment())
        }

        return fragmentThirdScreenBinding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        fragmentThirdScreenBinding = null
    }
}