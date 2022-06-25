package com.example.metroid.ui.view.main_Cycle.train

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.metroid.databinding.FragmentConfirmationTicketShowBinding
import com.example.metroid.model.remote.responses.SeatModel
import com.example.metroid.model.remote.responses.TicketModel
import com.example.metroid.model.remote.responses.TicketInfoData
import com.example.metroid.ui.view.viewmodel.main_cycle.ReservationViewModel
import com.shashank.sony.fancytoastlib.FancyToast
import kotlinx.coroutines.*
import java.lang.Exception
import java.time.format.DateTimeFormatter


class ConfirmationTicketShowFragment : Fragment() {
    private lateinit var mReservationViewModel: ReservationViewModel
    private lateinit var fragmentConfirmationTicketShowBinding: FragmentConfirmationTicketShowBinding
    private lateinit var listOfSeats: MutableList<SeatModel>
    private lateinit var ticketModel: TicketModel
    private var userId: Long = -1
    private lateinit var ticketInfoData: TicketInfoData

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        fragmentConfirmationTicketShowBinding =
            FragmentConfirmationTicketShowBinding.inflate(inflater)

        mReservationViewModel =
            ViewModelProvider(requireActivity())[ReservationViewModel::class.java]
        runBlocking {

                val userData = mReservationViewModel.getPrefData(requireActivity())
                userId = userData.id
                ticketInfoData = mReservationViewModel.getTicketInfo(userId)

        }

     try {
         fragmentConfirmationTicketShowBinding.tvPrice.text = "${ticketInfoData.price} L.E"
         fragmentConfirmationTicketShowBinding.tvStateFrom.text = "${ticketInfoData.stateArr}"
         fragmentConfirmationTicketShowBinding.tvStateTo.text = "${ticketInfoData.stateDept}"
         fragmentConfirmationTicketShowBinding.tvUserName.text = "${ticketInfoData.firstName}"
         fragmentConfirmationTicketShowBinding.tvStationFromName.text = ticketInfoData.nameArr
         fragmentConfirmationTicketShowBinding.tvStationToName.text = ticketInfoData.nameDept
         fragmentConfirmationTicketShowBinding.tvSeats.text = ticketInfoData.seats.toString()
         fragmentConfirmationTicketShowBinding.tvTripId.text = ticketInfoData.tripId.toString()


         fragmentConfirmationTicketShowBinding.tvConfirmedAt.text = ticketInfoData.confirmDate
         val format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")
         val formatDateTime: String = ticketInfoData.arrTime.format(format)
         val formatDateTime2: String = ticketInfoData.deptTime.format(format)

         fragmentConfirmationTicketShowBinding.tvArrCalender.text = formatDateTime
         fragmentConfirmationTicketShowBinding.tvDeptCalender.text = formatDateTime2

     }catch (e :Exception){
         Log.e("asd",e.toString())
         FancyToast.makeText(
             requireActivity(),
             "$e",
             FancyToast.LENGTH_LONG,
             FancyToast.ERROR,
             true
         ).show()
     }

        return fragmentConfirmationTicketShowBinding.root
    }


}