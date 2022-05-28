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

            if (password != confirmPassword){
                Toast.makeText(requireActivity(), "password not match confirm", Toast.LENGTH_SHORT).show()
            }

            if(!checkEmail(email)){
                Toast.makeText(requireActivity(), "enter valid email", Toast.LENGTH_SHORT).show()
            }

            val body = RegisterBody(firstName, secondName,email,password)
            viewLifecycleOwner.lifecycleScope.launch {
                var checkRequest=mLoginViewModel.register(body)
                if (checkRequest){
                    Toast.makeText(requireActivity(), "check your email for confirmation", Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(requireActivity(), "plz enter valid information", Toast.LENGTH_SHORT).show()
                }

            }

        }


        return fragmentSignUpBinding.root
    }


}