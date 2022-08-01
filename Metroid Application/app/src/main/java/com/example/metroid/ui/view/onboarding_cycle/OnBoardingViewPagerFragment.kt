package com.example.metroid.ui.view.onboarding_cycle

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.metroid.databinding.FragmentOnBoardingViewPagerBinding
import com.example.metroid.ui.adapter.OnBoardingViewPagerAdapter
import com.example.metroid.ui.view.main_Cycle.HomeActivity
import com.example.metroid.ui.view.onboarding_cycle.screen.FirstScreen
import com.example.metroid.ui.view.onboarding_cycle.screen.SecondScreen
import com.example.metroid.ui.view.onboarding_cycle.screen.ThirdScreen
import com.example.metroid.ui.view.viewmodel.login_cycle.LoginViewModel
import com.example.metroid.ui.view.viewmodel.main_cycle.MetroViewModel
import kotlinx.coroutines.launch


class OnBoardingViewPagerFragment : Fragment() {

    private var _fragmentOnBoardingViewPagerBinding: FragmentOnBoardingViewPagerBinding? = null
    lateinit var mViewModel: MetroViewModel

    private val fragmentOnBoardingViewPagerBinding get() = _fragmentOnBoardingViewPagerBinding!!

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
    ): View {
        // Inflate the layout for this fragment
        _fragmentOnBoardingViewPagerBinding =
            FragmentOnBoardingViewPagerBinding.inflate(layoutInflater)
        mViewModel = ViewModelProvider(requireActivity())[MetroViewModel::class.java]
        var id = -1L
        viewLifecycleOwner.lifecycleScope.launch {
            val userInfo = mViewModel.getPrefData(requireActivity())
            id = userInfo.id

            if (id != -1L) {
                startActivity(Intent(requireActivity(), HomeActivity::class.java))
                requireActivity().finish()
            }
        }


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

        fragmentOnBoardingViewPagerBinding.fragmentVp.adapter = adapter
        //  fragmentOnBoardingViewPagerBinding?.fragmentVp?.registerOnPageChangeCallback()
        return fragmentOnBoardingViewPagerBinding.root


    }


    override fun onDestroyView() {
        super.onDestroyView()
        _fragmentOnBoardingViewPagerBinding = null
    }
}