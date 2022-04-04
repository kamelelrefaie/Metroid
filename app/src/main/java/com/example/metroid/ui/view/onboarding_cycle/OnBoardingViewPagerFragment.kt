package com.example.metroid.ui.view.onboarding_cycle

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.metroid.databinding.FragmentOnBoardingViewPagerBinding
import com.example.metroid.ui.adapter.OnBoardingViewPagerAdapter
import com.example.metroid.ui.view.onboarding_cycle.screen.FirstScreen
import com.example.metroid.ui.view.onboarding_cycle.screen.SecondScreen
import com.example.metroid.ui.view.onboarding_cycle.screen.ThirdScreen


class OnBoardingViewPagerFragment : Fragment() {

    private var fragmentOnBoardingViewPagerBinding: FragmentOnBoardingViewPagerBinding? = null

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
        fragmentOnBoardingViewPagerBinding =
            FragmentOnBoardingViewPagerBinding.inflate(layoutInflater)

        val fragmentList = arrayListOf<Fragment>(
            FirstScreen(),
            SecondScreen(),
            ThirdScreen(),
        )

        val adapter = OnBoardingViewPagerAdapter(
            fragmentList,
            requireActivity().supportFragmentManager,
            lifecycle
        )

        fragmentOnBoardingViewPagerBinding?.fragmentVp?.adapter = adapter

        return fragmentOnBoardingViewPagerBinding?.root


    }


    override fun onDestroyView() {
        super.onDestroyView()
        fragmentOnBoardingViewPagerBinding = null
    }
}