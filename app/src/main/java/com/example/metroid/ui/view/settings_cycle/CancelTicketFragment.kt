package com.example.metroid.ui.view.settings_cycle

import android.content.Context
import android.content.Context.CONNECTIVITY_SERVICE
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkCapabilities.NET_CAPABILITY_INTERNET
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.metroid.databinding.FragmentCancelTicketBinding
import com.example.metroid.model.remote.responses.TicketInfoData
import com.example.metroid.ui.view.viewmodel.main_cycle.ReservationViewModel
import kotlinx.coroutines.*
import java.lang.Exception
import java.time.format.DateTimeFormatter


class CancelTicketFragment : Fragment() {
    private lateinit var mReservationViewModel: ReservationViewModel
    private lateinit var fragmentCancelTicketBinding: FragmentCancelTicketBinding
    private var userId: Long = -1
    private lateinit var ticketInfoData: TicketInfoData
    private var drawState: Boolean = true

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        fragmentCancelTicketBinding = FragmentCancelTicketBinding.inflate(inflater)
        mReservationViewModel =
            ViewModelProvider(requireActivity())[ReservationViewModel::class.java]


        if (!checkForInternet(requireActivity())) {
            drawLayout(false)
        }

        fragmentCancelTicketBinding.included.tryAgainButton.setOnClickListener {
            runBlocking {
                try {
                    val userData = mReservationViewModel.getPrefData(requireActivity())
                    userId = userData.id
                    ticketInfoData = mReservationViewModel.getTicketInfo(userId)
                    changeLayout()
                    drawLayout(true)
                } catch (e: Exception) {
                    Toast.makeText(
                        requireActivity(),
                        "please, check your internet connection",
                        Toast.LENGTH_SHORT
                    ).show()
                    drawLayout(false)
                }
            }
        }

        runBlocking {
            try {
                val userData = mReservationViewModel.getPrefData(requireActivity())
                userId = userData.id
                ticketInfoData = mReservationViewModel.getTicketInfo(userId)
                changeLayout()

            } catch (e: Exception) {
                Toast.makeText(
                    requireActivity(),
                    "please, check your internet connection",
                    Toast.LENGTH_SHORT
                ).show()
                drawLayout(false)
            }
        }


        return fragmentCancelTicketBinding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun changeLayout() {
        try {
            if (ticketInfoData.message == 1) {
                fragmentCancelTicketBinding.tvPrice.setText("${ticketInfoData.price} L.E")
                fragmentCancelTicketBinding.tvStateFrom.setText("${ticketInfoData.stateArr}")
                fragmentCancelTicketBinding.tvStateTo.setText("${ticketInfoData.stateDept}")
                fragmentCancelTicketBinding.tvUserName.setText("${ticketInfoData.firstName}")
                fragmentCancelTicketBinding.tvStationFromName.setText(ticketInfoData.nameArr)
                fragmentCancelTicketBinding.tvStationToName.setText(ticketInfoData.nameDept)
                fragmentCancelTicketBinding.tvSeats.setText(ticketInfoData.seats.toString())
                fragmentCancelTicketBinding.tvTripId.setText(ticketInfoData.tripId.toString())


                fragmentCancelTicketBinding.tvConfirmedAt.setText(ticketInfoData.confirmDate)
                val format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")
                val formatDateTime: String = ticketInfoData.arrTime.format(format)
                val formatDateTime2: String = ticketInfoData.deptTime.format(format)

                fragmentCancelTicketBinding.tvArrCalender.setText(formatDateTime)
                fragmentCancelTicketBinding.tvDeptCalender.setText(formatDateTime2)

                fragmentCancelTicketBinding.included.root.visibility = View.GONE

                fragmentCancelTicketBinding.btnCancel.setOnClickListener {
                    viewLifecycleOwner.lifecycleScope.launch {
                        val response = mReservationViewModel.deleteTicket(userId)
                        Toast.makeText(requireActivity(), response.message, Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            } else {
                fragmentCancelTicketBinding.ly.visibility = View.GONE
                fragmentCancelTicketBinding.ly2.visibility = View.GONE
                fragmentCancelTicketBinding.btnCancel.visibility = View.GONE
                fragmentCancelTicketBinding.included.root.visibility = View.GONE
                fragmentCancelTicketBinding.lyText.visibility = View.VISIBLE
                fragmentCancelTicketBinding.btnBuy.setOnClickListener {
                    findNavController().navigate(CancelTicketFragmentDirections.actionCancelTicketFragmentToReservationFragment())
                }
            }
        } catch (e: Exception) {
            Toast.makeText(
                requireActivity(),
                "please check your internet connection.",
                Toast.LENGTH_SHORT
            ).show()
        }

    }

    private fun drawLayout(draw: Boolean) {
        if (draw) {
            fragmentCancelTicketBinding.ly.visibility = View.VISIBLE
            fragmentCancelTicketBinding.ly2.visibility = View.VISIBLE
            fragmentCancelTicketBinding.btnCancel.visibility = View.VISIBLE
            fragmentCancelTicketBinding.included.root.visibility = View.GONE
        } else {
            fragmentCancelTicketBinding.ly.visibility = View.GONE
            fragmentCancelTicketBinding.ly2.visibility = View.GONE
            fragmentCancelTicketBinding.btnCancel.visibility = View.GONE
            fragmentCancelTicketBinding.included.root.visibility = View.VISIBLE
        }
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