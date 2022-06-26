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
    private var counter = 3
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        fragmentLoginBinding = FragmentLoginBinding.inflate(layoutInflater)


        mLoginViewModel = ViewModelProvider(requireActivity())[LoginViewModel::class.java]
        fragmentLoginBinding.btnLogin.setOnClickListener {

            val email = fragmentLoginBinding.inputEmail.text.toString()
            val password = fragmentLoginBinding.inputPassword.text.toString()

            if (counter == 0) {
                counter = 3
                findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToForgotPasswordFragment())
                FancyToast.makeText(
                    requireActivity(),
                    "you entered your password so many times, you can get it now from here",
                    FancyToast.LENGTH_LONG,
                    FancyToast.ERROR,
                    true
                ).show()
            } else if (email.isEmpty() || password.isEmpty()) {

                FancyToast.makeText(
                    requireActivity(),
                    "please all fields are required",
                    FancyToast.LENGTH_LONG,
                    FancyToast.ERROR,
                    true
                ).show()
            } else if (!checkEmail(email)) {
                FancyToast.makeText(
                    requireActivity(),
                    "enter valid email",
                    FancyToast.LENGTH_LONG,
                    FancyToast.ERROR,
                    true
                ).show()
            } else {
                viewLifecycleOwner.lifecycleScope.launch {

                    if (mLoginViewModel.login(email, password)) {
                        FancyToast.makeText(
                            requireActivity(),
                            "login success",
                            FancyToast.LENGTH_LONG,
                            FancyToast.SUCCESS,
                            true
                        ).show()
                        val nameIdRequest = mLoginViewModel.getNameId(email)
                        mLoginViewModel.saveNameAndUserIdToDataStore(
                            nameIdRequest.name,
                            nameIdRequest.id.toLong(),
                            requireActivity()
                        )

                        startActivity(Intent(requireActivity(), HomeActivity::class.java))
                        requireActivity().finish()
                    } else {
                        FancyToast.makeText(
                            requireActivity(),
                            "Wrong email and Password",
                            FancyToast.LENGTH_LONG,
                            FancyToast.ERROR,
                            true
                        ).show()
                        counter--

                    }

                }
            }

        }

        fragmentLoginBinding.forgotPassword.setOnClickListener {
            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToForgotPasswordFragment())
        }
        fragmentLoginBinding.gotoRegister.setOnClickListener {
            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToSignUpFragment())
        }

        return fragmentLoginBinding.root
    }


}