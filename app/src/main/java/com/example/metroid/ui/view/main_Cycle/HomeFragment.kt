package com.example.metroid.ui.view.main_Cycle

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.metroid.R
import com.example.metroid.databinding.FragmentHomeBinding
import java.lang.Exception


class HomeFragment : Fragment() {

   private lateinit var fragmentHomeBinding: FragmentHomeBinding
   

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
       fragmentHomeBinding = FragmentHomeBinding.inflate(layoutInflater)

        fragmentHomeBinding.ivNfc.let {
            Glide.with(requireActivity())
                .load(R.drawable.ic_nfc).circleCrop().into(it)
        }

        try {
            fragmentHomeBinding.ivNfc.setOnClickListener {
                startActivity(Intent(requireActivity(), NfcPageActivity::class.java))
            }
        }catch (e:Exception){

        }


        fragmentHomeBinding.ivProfilePic.let {
            Glide.with(requireActivity()).load(R.drawable.ic_person).circleCrop().into(it)
        }

return fragmentHomeBinding.root
    }


}