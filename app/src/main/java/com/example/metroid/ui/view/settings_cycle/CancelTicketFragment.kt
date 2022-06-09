package com.example.metroid.ui.view.settings_cycle

import android.content.Context
import android.content.Context.CONNECTIVITY_SERVICE
import android.net.ConnectivityManager
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
        mReservationViewModel = ViewModelProvider(requireActivity())[ReservationViewModel::class.java]



        fragmentCancelTicketBinding.included.tryAgainButton.setOnClickListener {
            runBlocking {
                try {
                    val userData = mReservationViewModel.getPrefData(requireActivity())
                    userId = userData.id
                    ticketInfoData = mReservationViewModel.getTicketInfo(userId)
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
            changeLayout()
        }

        runBlocking {
            try {
                val userData = mReservationViewModel.getPrefData(requireActivity())
                userId = userData.id
                ticketInfoData = mReservationViewModel.getTicketInfo(userId)

            } catch (e: Exception) {
                Toast.makeText(
                    requireActivity(),
                    "please, check your internet connection",
                    Toast.LENGTH_SHORT
                ).show()
                drawLayout(false)
            }
        }

        changeLayout()

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
}