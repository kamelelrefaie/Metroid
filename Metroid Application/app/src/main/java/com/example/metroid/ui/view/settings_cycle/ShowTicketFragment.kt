package com.example.metroid.ui.view.settings_cycle


import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.metroid.databinding.FragmentShowTicketBinding
import com.example.metroid.model.remote.responses.TicketInfoData
import com.example.metroid.ui.view.viewmodel.main_cycle.ReservationViewModel
import com.shashank.sony.fancytoastlib.FancyToast
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

        if(!checkForInternet(requireActivity())){
            drawLayout(false)
        }

        fragmentShowTicketBinding.included.tryAgainButton.setOnClickListener {
            runBlocking {
                try {
                    val userData = mReservationViewModel.getPrefData(requireActivity())
                    userId = userData.id
                    ticketInfoData = mReservationViewModel.getTicketInfo(userId)
                    changeLayout()
                    drawLayout(true)
                } catch (e: Exception) {
                    FancyToast.makeText(
                        requireActivity(),
                        "please, check your internet connection",
                        FancyToast.LENGTH_LONG,
                        FancyToast.ERROR,
                        true
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
                FancyToast.makeText(
                    requireActivity(),
                    "please, check your internet connection",
                    FancyToast.LENGTH_LONG,
                    FancyToast.ERROR,
                    true
                ).show()
                drawLayout(false)
            }
        }



        return fragmentShowTicketBinding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun changeLayout(){
        try {
            if (ticketInfoData.message == 1) {
                fragmentShowTicketBinding.tvPrice.text = "${ticketInfoData.price} L.E"
                fragmentShowTicketBinding.tvStateFrom.text = ticketInfoData.stateArr
                fragmentShowTicketBinding.tvStateTo.text = ticketInfoData.stateDept
                fragmentShowTicketBinding.tvUserName.text = ticketInfoData.firstName
                fragmentShowTicketBinding.tvStationFromName.text = ticketInfoData.nameArr
                fragmentShowTicketBinding.tvStationToName.text = ticketInfoData.nameDept
                fragmentShowTicketBinding.tvSeats.text = ticketInfoData.seats.toString()
                fragmentShowTicketBinding.tvTripId.text = ticketInfoData.tripId.toString()


                fragmentShowTicketBinding.tvConfirmedAt.text = ticketInfoData.confirmDate
                val format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")
                val formatDateTime: String = ticketInfoData.arrTime.format(format)
                val formatDateTime2: String = ticketInfoData.deptTime.format(format)

                fragmentShowTicketBinding.tvArrCalender.text = formatDateTime
                fragmentShowTicketBinding.tvDeptCalender.text = formatDateTime2

                fragmentShowTicketBinding.included.root.visibility = View.GONE

            } else {
                fragmentShowTicketBinding.ly.visibility = View.GONE
                fragmentShowTicketBinding.ly2.visibility = View.GONE
                fragmentShowTicketBinding.included.root.visibility = View.GONE
                fragmentShowTicketBinding.lyText.visibility = View.VISIBLE
                fragmentShowTicketBinding.btnBuy.setOnClickListener {
                    findNavController().navigate(ShowTicketFragmentDirections.actionShowTicketFragmentToReservationFragment())

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



    private fun checkForInternet(context: Context): Boolean {

        // register activity with the connectivity manager service
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

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