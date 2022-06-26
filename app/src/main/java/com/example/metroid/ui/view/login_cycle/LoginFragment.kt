package com.example.metroid.ui.view.login_cycle

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
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
                if (checkForInternet(requireActivity())){
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
            }else{
                    FancyToast.makeText(
                        requireActivity(),
                        "Check your internet connection",
                        FancyToast.LENGTH_LONG,
                        FancyToast.ERROR,
                        true
                    ).show()
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

    private fun checkForInternet(context: Context): Boolean {

        // register activity with the connectivity manager service
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        // if the android version is equal to M
        // or greater we need to use the
        // NetworkCapabilities to check what type of
        // network has the internet connection
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            // Returns a Network object corresponding to
            // the currently active default data network.
            val network = connectivityManager.activeNetwork ?: return false

            // Representation of the capabilities of an active network.
            val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false

            return when {
                // Indicates this network uses a Wi-Fi transport,
                // or WiFi has network connectivity
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true

                // Indicates this network uses a Cellular transport. or
                // Cellular has network connectivity
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true

                // else return false
                else -> false
            }
        } else {
            // if the android version is below M
            @Suppress("DEPRECATION") val networkInfo =
                connectivityManager.activeNetworkInfo ?: return false
            @Suppress("DEPRECATION")
            return networkInfo.isConnected
        }
    }

}