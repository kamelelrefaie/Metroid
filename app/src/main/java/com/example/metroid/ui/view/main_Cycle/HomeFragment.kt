package com.example.metroid.ui.view.main_Cycle

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.metroid.R
import com.example.metroid.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

   private var fragmentHomeBinding: FragmentHomeBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       fragmentHomeBinding = FragmentHomeBinding.inflate(layoutInflater)

        fragmentHomeBinding?.ivNfc?.let {
            Glide.with(requireActivity())
                .load(R.drawable.ic_nfc).circleCrop().into(it)
        }

        fragmentHomeBinding?.ivProfilePic?.let {
            Glide.with(requireActivity()).load(R.drawable.kamel).circleCrop().into(it)
        }

return fragmentHomeBinding?.root
    }


}