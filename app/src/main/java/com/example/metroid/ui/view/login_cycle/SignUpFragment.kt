package com.example.metroid.ui.view.login_cycle

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.metroid.R
import com.example.metroid.databinding.FragmentLoginBinding
import com.example.metroid.databinding.FragmentSignUpBinding

class SignUpFragment : Fragment() {

    private var fragmentSignUpBinding: FragmentSignUpBinding? = null
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
        fragmentSignUpBinding = FragmentSignUpBinding.inflate(layoutInflater)




        return fragmentSignUpBinding?.root
    }


}