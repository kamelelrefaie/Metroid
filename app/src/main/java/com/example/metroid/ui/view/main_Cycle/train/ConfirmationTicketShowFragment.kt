package com.example.metroid.ui.view.main_Cycle.train

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.example.metroid.R
import com.example.metroid.databinding.FragmentConfirmTicketBinding
import com.example.metroid.databinding.FragmentConfirmationTicketShowBinding
import com.example.metroid.model.remote.responses.SeatModel
import com.example.metroid.model.remote.responses.TicketModel

import com.example.metroid.ui.view.viewmodel.main_cycle.ReservationViewModel
import com.example.metroid.utils.Constants
import kotlinx.coroutines.launch


class ConfirmationTicketShowFragment : Fragment() {
    private lateinit var mReservationViewModel: ReservationViewModel
    private lateinit var fragmentConfirmationTicketShowBinding: FragmentConfirmationTicketShowBinding
    private lateinit var listOfSeats: MutableList<SeatModel>
    private lateinit var ticketModel: TicketModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment


        fragmentConfirmationTicketShowBinding = FragmentConfirmationTicketShowBinding.inflate(inflater)

        mReservationViewModel =
            ViewModelProvider(requireActivity())[ReservationViewModel::class.java]




        return fragmentConfirmationTicketShowBinding.root
    }





}