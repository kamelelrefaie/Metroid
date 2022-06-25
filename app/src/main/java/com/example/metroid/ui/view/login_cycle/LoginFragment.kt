package com.example.metroid.ui.view.login_cycle

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.metroid.R
import com.example.metroid.databinding.FragmentLoginBinding
import com.example.metroid.ui.view.main_Cycle.HomeActivity
import com.example.metroid.ui.view.viewmodel.login_cycle.LoginViewModel
import com.example.metroid.utils.Constants.checkEmail
import com.shashank.sony.fancytoastlib.FancyToast
import kotlinx.coroutines.launch


class LoginFragment : Fragment() {

    private lateinit var fragmentLoginBinding: FragmentLoginBinding

    lateinit var mLoginViewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        fragmentLoginBinding = FragmentLoginBinding.inflate(layoutInflater)
        fragmentLoginBinding.forgotPassword.setOnClickListener {
            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToForgotPasswordFragment())
        }

        mLoginViewModel = ViewModelProvider(requireActivity())[LoginViewModel::class.java]
        fragmentLoginBinding.btnLogin.setOnClickListener {
            viewLifecycleOwner.lifecycleScope.launch {
                mLoginViewModel.saveNameAndUserIdToDataStore("kamel",1,requireActivity())
                startActivity(Intent(requireActivity(), HomeActivity::class.java))

            }



//            val email = fragmentLoginBinding.inputEmail.text.toString()
//            val password = fragmentLoginBinding.inputPassword.text.toString()
//
//            viewLifecycleOwner.lifecycleScope.launch {
//
//                if (mLoginViewModel.login(email, password)) {
//                    FancyToast.makeText(
//                        requireActivity(),
//                        "login success",
//                        FancyToast.LENGTH_LONG,
//                        FancyToast.INFO,
//                        true
//                    ).show()
//                    startActivity(Intent(requireActivity(), HomeActivity::class.java))
//                    requireActivity().finish()
//
//                } else
//                    FancyToast.makeText(
//                        requireActivity(),
//                        "login failure , type your password and email again",
//                        FancyToast.LENGTH_LONG,
//                        FancyToast.INFO,
//                        true
//                    ).show()
//            }



        }


        fragmentLoginBinding.gotoRegister.setOnClickListener {
            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToSignUpFragment())
        }

        return fragmentLoginBinding.root
    }


}