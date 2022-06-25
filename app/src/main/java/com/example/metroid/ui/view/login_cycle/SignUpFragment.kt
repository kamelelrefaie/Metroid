package com.example.metroid.ui.view.login_cycle

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
import com.example.metroid.databinding.FragmentSignUpBinding
import com.example.metroid.model.remote.responses.RegisterBody
import com.example.metroid.ui.view.viewmodel.login_cycle.LoginViewModel
import com.example.metroid.utils.Constants.checkEmail
import com.shashank.sony.fancytoastlib.FancyToast
import kotlinx.coroutines.launch

class SignUpFragment : Fragment() {
    lateinit var mLoginViewModel: LoginViewModel

    private lateinit var fragmentSignUpBinding: FragmentSignUpBinding
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
        fragmentSignUpBinding = FragmentSignUpBinding.inflate(layoutInflater)
        mLoginViewModel = ViewModelProvider(requireActivity())[LoginViewModel::class.java]

        fragmentSignUpBinding.btnSignUp.setOnClickListener {
            val firstName = fragmentSignUpBinding.inputFirstName.text.toString()
            val secondName = fragmentSignUpBinding.inputSecondName.text.toString()
            val email = fragmentSignUpBinding.inputEmail.text.toString()
            val password = fragmentSignUpBinding.inputPassword.text.toString()
            val confirmPassword = fragmentSignUpBinding.inputConfirmPassword.text.toString()

            if (password != confirmPassword) {
                FancyToast.makeText(
                    requireActivity(),
                    "password not match confirm",
                    FancyToast.LENGTH_LONG,
                    FancyToast.ERROR,
                    true
                ).show()
            }

            if (!checkEmail(email)) {
                FancyToast.makeText(
                    requireActivity(),
                    "enter valid email",
                    FancyToast.LENGTH_LONG,
                    FancyToast.ERROR,
                    true
                ).show()
            }

            val body = RegisterBody(firstName, secondName, email, password)
            viewLifecycleOwner.lifecycleScope.launch {
                val checkRequest = mLoginViewModel.register(body)
                if (checkRequest) {
                    FancyToast.makeText(
                        requireActivity(),
                        "check your email for confirmation",
                        FancyToast.LENGTH_LONG,
                        FancyToast.INFO,
                        true
                    ).show()
                } else {
                    FancyToast.makeText(
                        requireActivity(),
                        "enter valid information",
                        FancyToast.LENGTH_LONG,
                        FancyToast.ERROR,
                        true
                    ).show()
                }

            }

        }


        return fragmentSignUpBinding.root
    }


}