package com.example.metroid.ui.view.main_Cycle.train

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.metroid.databinding.FragmentReservationBinding
import com.example.metroid.databinding.FragmentTrainsBinding
import com.example.metroid.databinding.NoInternetConnectionBinding
import com.example.metroid.model.remote.responses.TripResponseItem
import com.example.metroid.ui.adapter.ReservationAdapter
import com.example.metroid.ui.view.viewmodel.main_cycle.ReservationViewModel
import com.shashank.sony.fancytoastlib.FancyToast
import kotlinx.android.synthetic.main.activity_on_boarding.*
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.Month
import java.util.*
import kotlin.collections.ArrayList

class TrainsFragment : Fragment() {
    private val args by navArgs<TrainsFragmentArgs>()
    private lateinit var mReservationViewModel: ReservationViewModel
    private lateinit var fragmentTrainsBinding: FragmentTrainsBinding
    private lateinit var arrayList: ArrayList<TripResponseItem>
    private lateinit var adapter: ReservationAdapter

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        fragmentTrainsBinding = FragmentTrainsBinding.inflate(inflater)
        mReservationViewModel =
            ViewModelProvider(requireActivity())[ReservationViewModel::class.java]

        intiAdapter()


        return fragmentTrainsBinding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun intiAdapter() {
        try {
            fragmentTrainsBinding.rvReservation.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            var userId = -1L
            viewLifecycleOwner.lifecycleScope.launch {
                val userModel = mReservationViewModel.getPrefData(requireActivity())
                userId = userModel.id
            }
            adapter = ReservationAdapter(fragment = this, userId = userId)
            fragmentTrainsBinding.tilSearch.setOnClickListener {
                DatePickerDialog(requireActivity(), onDateSet(), 2022, 5, 1).show()
            }
            viewLifecycleOwner.lifecycleScope.launch {
                arrayList = mReservationViewModel.getTrainTime(
                    args.stationResponse.from.toInt(),
                    args.stationResponse.to.toInt()
                )
                adapter.setTrainList(arrayList)
                if (arrayList.isEmpty()) {
                    fragmentTrainsBinding.noTrain.visibility = View.VISIBLE
                    fragmentTrainsBinding.rvReservation.visibility = View.GONE
                } else {
                    fragmentTrainsBinding.rvReservation.adapter = adapter
                }
            }
        } catch (e: Exception) {
            FancyToast.makeText(
                requireActivity(),
                "please, check your internet connection",
                FancyToast.LENGTH_LONG,
                FancyToast.ERROR,
                true
            ).show()
        }

    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun onDateSet(): DatePickerDialog.OnDateSetListener {
        return DatePickerDialog.OnDateSetListener { datePicker, year, month, day ->
            fragmentTrainsBinding.tilSearch.setText(
                "" + LocalDate.of(
                    year,
                    Month.of(month + 1),
                    day
                )
            )
            viewLifecycleOwner.lifecycleScope.launch {
                arrayList = mReservationViewModel.getTrainAtSpecificTime(
                    args.stationResponse.from.toInt(),
                    args.stationResponse.to.toInt(),
                    LocalDate.of(
                        year,
                        Month.of(month + 1),
                        day
                    )
                )
                adapter.setTrainList(arrayList)
            }
        }
    }


}