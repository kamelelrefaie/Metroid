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
import com.example.metroid.databinding.FragmentForgotPasswordBinding
import com.example.metroid.databinding.FragmentLoginBinding
import com.example.metroid.ui.view.viewmodel.login_cycle.LoginViewModel
import com.example.metroid.utils.Constants.checkEmail
import kotlinx.coroutines.launch


class ForgotPasswordFragment : Fragment() {
    private lateinit var fragmentForgotPasswordBinding: FragmentForgotPasswordBinding

    lateinit var mLoginViewModel: LoginViewModel
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
        fragmentForgotPasswordBinding = FragmentForgotPasswordBinding.inflate(layoutInflater)
        mLoginViewModel = ViewModelProvider(requireActivity())[LoginViewModel::class.java]

        fragmentForgotPasswordBinding.btnLogin.setOnClickListener {
            var email = fragmentForgotPasswordBinding.inputEmail.text.toString()
            if (!checkEmail(email)) {
                Toast.makeText(requireActivity(), "email not valid", Toast.LENGTH_SHORT).show()
            }
            viewLifecycleOwner.lifecycleScope.launch {
                if (mLoginViewModel.requestPassword(email)) {
                    Toast.makeText(
                        requireActivity(),
                        "your new password sent successfully",
                        Toast.LENGTH_SHORT
                    ).show()
                    findNavController().navigate(ForgotPasswordFragmentDirections.actionForgotPasswordFragmentToLoginFragment())
                } else {
                    Toast.makeText(requireActivity(), "write a valid email", Toast.LENGTH_SHORT)
                        .show()
                }
            }

        }
        return fragmentForgotPasswordBinding.root
    }


}