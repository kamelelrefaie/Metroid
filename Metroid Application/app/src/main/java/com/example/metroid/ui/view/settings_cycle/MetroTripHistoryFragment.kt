package com.example.metroid.ui.view.settings_cycle

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.metroid.databinding.FragmentMetroTripHistoryBinding
import com.example.metroid.model.remote.responses.GetMetroTripsHistoryFromApiItem
import com.example.metroid.ui.adapter.TripHistoryAdapter
import com.example.metroid.ui.view.viewmodel.main_cycle.MetroViewModel
import com.shashank.sony.fancytoastlib.FancyToast
import kotlinx.coroutines.launch

class MetroTripHistoryFragment : Fragment() {

    private lateinit var binding: FragmentMetroTripHistoryBinding
    private lateinit var metroViewModel: MetroViewModel
    private lateinit var arrayList: ArrayList<GetMetroTripsHistoryFromApiItem>
    private lateinit var adapter: TripHistoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentMetroTripHistoryBinding.inflate(inflater)
        metroViewModel = ViewModelProvider(requireActivity())[MetroViewModel::class.java]
        arrayList = ArrayList()
        initAdapter()
        return binding.root
    }

    private fun initAdapter() {

        try {
            binding.rvMetroTripHistory.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

            adapter = TripHistoryAdapter()

            viewLifecycleOwner.lifecycleScope.launch {
                arrayList = metroViewModel.getMetroTrips(requireActivity())

                adapter.setList(arrayList)

                if (arrayList.isEmpty()) {
                    binding.tvNoTrips.visibility = View.VISIBLE
                    binding.rvMetroTripHistory.visibility = View.GONE
                } else {
                    binding.rvMetroTripHistory.adapter = adapter
                    binding.tvNoTrips.visibility = View.GONE
            }

            }

        } catch (e: Exception) {
            FancyToast.makeText(
                requireActivity(),
                "please, check your internet connection",
                FancyToast.LENGTH_LONG,
                FancyToast.ERROR,
                true
            ).show()        }

    }


}