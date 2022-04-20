package com.example.metroid.ui.view.main_Cycle

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.metroid.R
import com.example.metroid.data.StationData
import com.example.metroid.databinding.FragmentReservationBinding
import com.example.metroid.ui.adapter.ReservationAdapter

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class ReservationFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val fragmentReservationBinding
        :FragmentReservationBinding = FragmentReservationBinding.inflate(inflater)

        fragmentReservationBinding.rvReservation.layoutManager =LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
        val list = mutableListOf<StationData>()
        list.add(StationData("maadi",R.drawable.maadi_station))
        list.add(StationData("Ramses",R.drawable.maadi_station))
        list.add(StationData("cairo",R.drawable.maadi_station))
        list.add(StationData("alex",R.drawable.maadi_station))

        val adapter = ReservationAdapter()
        adapter.setStationList(list)

        fragmentReservationBinding.rvReservation.adapter = adapter


        return fragmentReservationBinding.root
    }


}