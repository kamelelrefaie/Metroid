package com.example.metroid.ui.view.login_cycle

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.metroid.R
import com.example.metroid.databinding.FragmentLoginBinding
import com.example.metroid.ui.view.main_Cycle.HomeActivity


class LoginFragment : Fragment() {

    private var fragmentLoginBinding: FragmentLoginBinding? = null
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
        fragmentLoginBinding = FragmentLoginBinding.inflate(layoutInflater)
        fragmentLoginBinding?.forgotPassword?.setOnClickListener {
            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToForgotPasswordFragment())
        }

        fragmentLoginBinding?.btnLogin?.setOnClickListener {
          startActivity(Intent(requireActivity(),HomeActivity::class.java))
        }

        fragmentLoginBinding?.gotoRegister?.setOnClickListener {
            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToSignUpFragment())
        }



        return fragmentLoginBinding?.root
    }


}