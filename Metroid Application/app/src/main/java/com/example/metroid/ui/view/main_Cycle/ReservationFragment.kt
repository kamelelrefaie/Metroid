package com.example.metroid.ui.view.main_Cycle

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.metroid.R
import com.example.metroid.model.StationData
import com.example.metroid.databinding.FragmentReservationBinding
import com.example.metroid.model.remote.responses.StationResponse
import com.example.metroid.ui.adapter.ReservationAdapter
import com.example.metroid.ui.view.viewmodel.login_cycle.LoginViewModel
import com.example.metroid.ui.view.viewmodel.main_cycle.ReservationViewModel
import com.example.metroid.utils.Constants.trainStationNameList
import com.shashank.sony.fancytoastlib.FancyToast
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.coroutines.launch


class ReservationFragment : Fragment() {

    private lateinit var mReservationViewModel: ReservationViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val fragmentReservationBinding
                : FragmentReservationBinding = FragmentReservationBinding.inflate(inflater)
        mReservationViewModel =
            ViewModelProvider(requireActivity())[ReservationViewModel::class.java]

        viewLifecycleOwner.lifecycleScope.launch {
            val userModel = mReservationViewModel.getPrefData(requireActivity())
            fragmentReservationBinding.rvUserName.text = userModel.name
        }


        val arrayAdapter = ArrayAdapter(
            requireActivity(),
            R.layout.item_list_reservation,
            trainStationNameList
        )

        fragmentReservationBinding.actvFrom.setAdapter(arrayAdapter)
        fragmentReservationBinding.actvTo.setAdapter(arrayAdapter)

        var firstStation = ""
        fragmentReservationBinding.actvFrom.setOnItemClickListener { adapterView, view, position, id ->
            firstStation = adapterView.getItemAtPosition(position).toString()

        }

        var endStation = ""

        fragmentReservationBinding.actvTo.setOnItemClickListener { adapterView, view, position, id ->
            endStation = adapterView.getItemAtPosition(position).toString()

        }

        fragmentReservationBinding.btnSearch.setOnClickListener {
            if (firstStation == endStation || firstStation.isEmpty() || endStation.isEmpty()) {
                FancyToast.makeText(
                    requireActivity(),
                    "please, choose right root",
                    FancyToast.LENGTH_LONG,
                    FancyToast.ERROR,
                    true
                ).show()
            } else {
                viewLifecycleOwner.lifecycleScope.launch {
                    try {
                    val stationResponse =
                        mReservationViewModel.getStationId(firstStation, endStation)
                    fragmentReservationBinding.actvTo.setText("")
                    fragmentReservationBinding.actvFrom.setText("")

                        findNavController().navigate(
                            ReservationFragmentDirections.actionReservationFragmentToTrainsFragment(
                                stationResponse
                            )
                        )
                        if (requireActivity() is HomeActivity) {
                            (activity as HomeActivity).hideBottomNavigationView()
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
            }
        }




        return fragmentReservationBinding.root
    }

    override fun onResume() {
        super.onResume()
        if (requireActivity() is HomeActivity) {
            (activity as HomeActivity).showBottomNavigationView()
        }
    }
}