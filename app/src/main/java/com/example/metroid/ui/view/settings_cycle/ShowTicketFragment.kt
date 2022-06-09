package com.example.metroid.ui.view.settings_cycle


import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.metroid.databinding.FragmentShowTicketBinding
import com.example.metroid.model.remote.responses.TicketInfoData
import com.example.metroid.ui.view.viewmodel.main_cycle.ReservationViewModel
import kotlinx.coroutines.*
import java.lang.Exception
import java.time.format.DateTimeFormatter


class ShowTicketFragment : Fragment() {
    private lateinit var mReservationViewModel: ReservationViewModel
    private lateinit var fragmentShowTicketBinding: FragmentShowTicketBinding
    private var userId: Long = -1
    private lateinit var ticketInfoData: TicketInfoData

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        fragmentShowTicketBinding =
            FragmentShowTicketBinding.inflate(inflater)

        mReservationViewModel =
            ViewModelProvider(requireActivity())[ReservationViewModel::class.java]

        fragmentShowTicketBinding.included.tryAgainButton.setOnClickListener {
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
        return fragmentShowTicketBinding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun changeLayout(){
        try {
            if (ticketInfoData.message == 1) {
                fragmentShowTicketBinding.tvPrice.setText("${ticketInfoData.price} L.E")
                fragmentShowTicketBinding.tvStateFrom.setText("${ticketInfoData.stateArr}")
                fragmentShowTicketBinding.tvStateTo.setText("${ticketInfoData.stateDept}")
                fragmentShowTicketBinding.tvUserName.setText("${ticketInfoData.firstName}")
                fragmentShowTicketBinding.tvStationFromName.setText(ticketInfoData.nameArr)
                fragmentShowTicketBinding.tvStationToName.setText(ticketInfoData.nameDept)
                fragmentShowTicketBinding.tvSeats.setText(ticketInfoData.seats.toString())
                fragmentShowTicketBinding.tvTripId.setText(ticketInfoData.tripId.toString())


                fragmentShowTicketBinding.tvConfirmedAt.setText(ticketInfoData.confirmDate)
                val format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")
                val formatDateTime: String = ticketInfoData.arrTime.format(format)
                val formatDateTime2: String = ticketInfoData.deptTime.format(format)

                fragmentShowTicketBinding.tvArrCalender.setText(formatDateTime)
                fragmentShowTicketBinding.tvDeptCalender.setText(formatDateTime2)
            } else {
                fragmentShowTicketBinding.ly.visibility = View.GONE
                fragmentShowTicketBinding.ly2.visibility = View.GONE
                fragmentShowTicketBinding.included.root.visibility = View.GONE
                fragmentShowTicketBinding.lyText.visibility = View.VISIBLE
                fragmentShowTicketBinding.btnBuy.setOnClickListener {

                }
            }
        } catch (e: Exception) {
            Toast.makeText(requireActivity(), "no internet connection", Toast.LENGTH_SHORT).show()
        }

    }
    private fun drawLayout(draw: Boolean) {
        if (draw) {
            fragmentShowTicketBinding.ly.visibility = View.VISIBLE
            fragmentShowTicketBinding.ly2.visibility = View.VISIBLE
            fragmentShowTicketBinding.included.root.visibility = View.GONE
        } else {
            fragmentShowTicketBinding.ly.visibility = View.GONE
            fragmentShowTicketBinding.ly2.visibility = View.GONE
            fragmentShowTicketBinding.included.root.visibility = View.VISIBLE
        }
    }
}