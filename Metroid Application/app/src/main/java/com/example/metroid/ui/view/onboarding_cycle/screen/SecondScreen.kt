package com.example.metroid.ui.view.onboarding_cycle.screen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.example.metroid.R
import com.example.metroid.databinding.FragmentSecondScreenBinding
import com.example.metroid.databinding.FragmentThirdScreenBinding



class SecondScreen : Fragment() {

    private var fragmentSecondScreenBinding: FragmentSecondScreenBinding? = null

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
        fragmentSecondScreenBinding =
            FragmentSecondScreenBinding.inflate(layoutInflater)

        val viewPager = activity?.findViewById<ViewPager2>(R.id.fragment_vp)
        fragmentSecondScreenBinding?.next?.setOnClickListener{
            viewPager?.currentItem =2
        }

        return fragmentSecondScreenBinding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        fragmentSecondScreenBinding = null
    }
}