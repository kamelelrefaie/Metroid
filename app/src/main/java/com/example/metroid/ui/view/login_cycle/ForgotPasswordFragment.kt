package com.example.metroid.ui.view.login_cycle

import android.content.Context
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
import com.example.metroid.databinding.FragmentForgotPasswordBinding
import com.example.metroid.databinding.FragmentLoginBinding
import com.example.metroid.ui.view.viewmodel.login_cycle.LoginViewModel
import com.example.metroid.utils.Constants.checkEmail
import com.shashank.sony.fancytoastlib.FancyToast
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
            val email = fragmentForgotPasswordBinding.inputEmail.text.toString()
            if (!checkEmail(email)) {
                FancyToast.makeText(
                    requireActivity(),
                    "write a valid email",
                    FancyToast.LENGTH_LONG,
                    FancyToast.ERROR,
                    true
                ).show()
            } else if (email.isEmpty()) {
                FancyToast.makeText(
                    requireActivity(),
                    "email field are required",
                    FancyToast.LENGTH_LONG,
                    FancyToast.ERROR,
                    true
                ).show()
            } else {
                if (checkForInternet(requireActivity())) {
                    viewLifecycleOwner.lifecycleScope.launch {
                        if (mLoginViewModel.requestPassword(email)) {
                            FancyToast.makeText(
                                requireActivity(),
                                "your new password sent successfully",
                                FancyToast.LENGTH_LONG,
                                FancyToast.SUCCESS,
                                true
                            ).show()
                            findNavController().navigate(ForgotPasswordFragmentDirections.actionForgotPasswordFragmentToLoginFragment())
                        } else {
                            FancyToast.makeText(
                                requireActivity(),
                                "you don't have an email register a new one now",
                                FancyToast.LENGTH_LONG,
                                FancyToast.WARNING,
                                true
                            ).show()

                        }
                    }
                } else {
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
        return fragmentForgotPasswordBinding.root
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