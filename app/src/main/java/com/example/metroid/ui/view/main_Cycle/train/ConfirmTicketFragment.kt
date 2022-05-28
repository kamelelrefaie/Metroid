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
import com.example.metroid.model.remote.responses.SeatModel
import com.example.metroid.model.remote.responses.TicketModel

import com.example.metroid.ui.view.viewmodel.main_cycle.ReservationViewModel
import com.example.metroid.utils.Constants
import kotlinx.coroutines.launch


class ConfirmTicketFragment : Fragment() {
    private val args by navArgs<ConfirmTicketFragmentArgs>()
    private lateinit var mReservationViewModel: ReservationViewModel
    private lateinit var fragmentConfirmTicketBinding: FragmentConfirmTicketBinding
    private lateinit var listOfSeats: MutableList<SeatModel>
    private lateinit var ticketModel: TicketModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment


        fragmentConfirmTicketBinding = FragmentConfirmTicketBinding.inflate(inflater)

        mReservationViewModel =
            ViewModelProvider(requireActivity())[ReservationViewModel::class.java]

        val arrayAdapter = ArrayAdapter(
            requireActivity(),
            R.layout.item_list_reservation,
            Constants.classList
        )

        fragmentConfirmTicketBinding.etClass.setAdapter(arrayAdapter)
        //classname
        var className = "A"

        fragmentConfirmTicketBinding.etClass.setOnItemClickListener { adapterView, view, position, id ->
            className = adapterView.getItemAtPosition(position).toString()
        }

        //quantity
        //price
        var quantity = 1
        var price = 0

        fragmentConfirmTicketBinding.etClass.doAfterTextChanged {
            fragmentConfirmTicketBinding.tieQuantity.setText("")
        }

        fragmentConfirmTicketBinding.tieQuantity.doAfterTextChanged {
            if (it.toString().isNotEmpty()) {
                quantity = it.toString().toInt()
                checkQuantity(it.toString().toInt())
                price = calculatePrice(className, quantity)
            }


        }


        //tripId
        val tripId = args.tripId
        //userId
        val userId = args.userId


        fragmentConfirmTicketBinding.btnConfirm.setOnClickListener {
            if (className.isEmpty() || quantity == 0 || price == 0) {
                Toast.makeText(requireActivity(), "fill with appropriate data", Toast.LENGTH_SHORT)
                    .show()
            } else {
                when (quantity) {
                    5 -> {
                        if (fragmentConfirmTicketBinding.tieSeatOne.text.isNullOrBlank() ||
                            fragmentConfirmTicketBinding.tieSeatNumberTwo.text.isNullOrBlank() ||
                            fragmentConfirmTicketBinding.tieSeatNumberThree.text.isNullOrBlank() ||
                            fragmentConfirmTicketBinding.tieSeatNumberFour.text.isNullOrBlank() ||
                            fragmentConfirmTicketBinding.tieSeatNumberFive.text.isNullOrBlank()
                        ) {
                            Toast.makeText(
                                requireActivity(),
                                "please fill seats",
                                Toast.LENGTH_SHORT
                            ).show()
                        } else {
                            listOfSeats = mutableListOf()
                            listOfSeats.add(
                                0,
                                SeatModel(
                                    fragmentConfirmTicketBinding.tieSeatOne.text.toString().toInt()
                                )
                            )
                            listOfSeats.add(
                                1,
                                SeatModel(
                                    fragmentConfirmTicketBinding.tieSeatNumberTwo.text.toString()
                                        .toInt()
                                )
                            )
                            listOfSeats.add(
                                2,
                                SeatModel(
                                    fragmentConfirmTicketBinding.tieSeatNumberThree.text.toString()
                                        .toInt()
                                )
                            )
                            listOfSeats.add(
                                3,
                                SeatModel(
                                    fragmentConfirmTicketBinding.tieSeatNumberFour.text.toString()
                                        .toInt()
                                )
                            )
                            listOfSeats.add(
                                4,
                                SeatModel(
                                    fragmentConfirmTicketBinding.tieSeatNumberFive.text.toString()
                                        .toInt()
                                )
                            )

                            //confirm ticket
                            ticketModel = TicketModel(className, price, listOfSeats, quantity)
                            viewLifecycleOwner.lifecycleScope.launch {
                                val message = mReservationViewModel.confirmTicketRequest(
                                    userId,
                                    tripId,
                                    ticketModel
                                )
                                if (message == "success") {
                                    Toast.makeText(requireActivity(), "success", Toast.LENGTH_SHORT)
                                        .show()
                                } else {
                                    Toast.makeText(
                                        requireActivity(),
                                        "$message",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }
                        }
                    }
                    4 -> {
                        if (fragmentConfirmTicketBinding.tieSeatOne.text.isNullOrBlank() ||
                            fragmentConfirmTicketBinding.tieSeatNumberTwo.text.isNullOrBlank() ||
                            fragmentConfirmTicketBinding.tieSeatNumberThree.text.isNullOrBlank() ||
                            fragmentConfirmTicketBinding.tieSeatNumberFour.text.isNullOrBlank()
                        ) {
                            Toast.makeText(
                                requireActivity(),
                                "please fill seats",
                                Toast.LENGTH_SHORT
                            ).show()
                        } else {
                            listOfSeats = mutableListOf()
                            listOfSeats.add(
                                0,
                                SeatModel(
                                    fragmentConfirmTicketBinding.tieSeatOne.text.toString().toInt()
                                )
                            )
                            listOfSeats.add(
                                1,
                                SeatModel(
                                    fragmentConfirmTicketBinding.tieSeatNumberTwo.text.toString()
                                        .toInt()
                                )
                            )
                            listOfSeats.add(
                                2,
                                SeatModel(
                                    fragmentConfirmTicketBinding.tieSeatNumberThree.text.toString()
                                        .toInt()
                                )
                            )
                            listOfSeats.add(
                                3,
                                SeatModel(
                                    fragmentConfirmTicketBinding.tieSeatNumberFour.text.toString()
                                        .toInt()
                                )
                            )


                            //confirm ticket
                            ticketModel = TicketModel(className, price, listOfSeats, quantity)
                            viewLifecycleOwner.lifecycleScope.launch {
                                val message = mReservationViewModel.confirmTicketRequest(
                                    userId,
                                    tripId,
                                    ticketModel
                                )
                                if (message == "success") {
                                    Toast.makeText(requireActivity(), "success", Toast.LENGTH_SHORT)
                                        .show()
                                } else {
                                    Toast.makeText(
                                        requireActivity(),
                                        "$message",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }
                        }
                    }
                    3 -> {
                        if (fragmentConfirmTicketBinding.tieSeatOne.text.isNullOrBlank() ||
                            fragmentConfirmTicketBinding.tieSeatNumberTwo.text.isNullOrBlank() ||
                            fragmentConfirmTicketBinding.tieSeatNumberThree.text.isNullOrBlank()

                        ) {
                            Toast.makeText(
                                requireActivity(),
                                "please fill seats",
                                Toast.LENGTH_SHORT
                            ).show()

                        } else {
                            if (fragmentConfirmTicketBinding.tieSeatOne.text.toString()
                                    .equals(fragmentConfirmTicketBinding.tieSeatNumberTwo.text.toString())
                                ||
                                fragmentConfirmTicketBinding.tieSeatOne.text.toString()
                                    .equals(fragmentConfirmTicketBinding.tieSeatNumberThree.text.toString())
                                ||
                                fragmentConfirmTicketBinding.tieSeatNumberTwo.text.toString()
                                    .equals(fragmentConfirmTicketBinding.tieSeatNumberThree.text.toString())
                            ) {
                                Toast.makeText(
                                    requireActivity(),
                                    "please don't choose same seat",
                                    Toast.LENGTH_SHORT
                                ).show()
                            } else {

                            listOfSeats = mutableListOf()
                            listOfSeats.add(
                                0,
                                SeatModel(
                                    fragmentConfirmTicketBinding.tieSeatOne.text.toString().toInt()
                                )
                            )
                            listOfSeats.add(
                                1,
                                SeatModel(
                                    fragmentConfirmTicketBinding.tieSeatNumberTwo.text.toString()
                                        .toInt()
                                )
                            )
                            listOfSeats.add(
                                2,
                                SeatModel(
                                    fragmentConfirmTicketBinding.tieSeatNumberThree.text.toString()
                                        .toInt()
                                )
                            )


                            //confirm ticket
                            ticketModel = TicketModel(className, price, listOfSeats, quantity)
                            viewLifecycleOwner.lifecycleScope.launch {
                                val message = mReservationViewModel.confirmTicketRequest(
                                    userId,
                                    tripId,
                                    ticketModel
                                )
                                if (message == "success") {
                                    Toast.makeText(requireActivity(), "success", Toast.LENGTH_SHORT)
                                        .show()
                                } else {
                                    Toast.makeText(
                                        requireActivity(),
                                        "$message",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }
                        }
                        }
                    }
                    2 -> {
                        if ((fragmentConfirmTicketBinding.tieSeatOne.text.isNullOrBlank() ||
                                    fragmentConfirmTicketBinding.tieSeatNumberTwo.text.isNullOrBlank())

                        ) {
                            Toast.makeText(
                                requireActivity(),
                                "please fill seats",
                                Toast.LENGTH_SHORT
                            ).show()
                        } else {
                            if ((fragmentConfirmTicketBinding.tieSeatOne.text.toString() ==
                                        fragmentConfirmTicketBinding.tieSeatNumberTwo.text.toString())
                            ) {
                                Toast.makeText(
                                    requireActivity(),
                                    "please don't choose same seat",
                                    Toast.LENGTH_SHORT
                                ).show()
                            } else {
                                listOfSeats = mutableListOf()
                                listOfSeats.add(
                                    0,
                                    SeatModel(
                                        fragmentConfirmTicketBinding.tieSeatOne.text.toString()
                                            .toInt()
                                    )
                                )
                                listOfSeats.add(
                                    1,
                                    SeatModel(
                                        fragmentConfirmTicketBinding.tieSeatNumberTwo.text.toString()
                                            .toInt()
                                    )
                                )

                                //confirm ticket
                                ticketModel = TicketModel(className, price, listOfSeats, quantity)
                                viewLifecycleOwner.lifecycleScope.launch {
                                    val message = mReservationViewModel.confirmTicketRequest(
                                        userId,
                                        tripId,
                                        ticketModel
                                    )
                                    if (message == "success") {
                                        Toast.makeText(
                                            requireActivity(),
                                            "success",
                                            Toast.LENGTH_SHORT
                                        )
                                            .show()
                                    } else {
                                        Toast.makeText(
                                            requireActivity(),
                                            "$message",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                }
                            }
                        }
                    }
                    1 -> {
                        if (fragmentConfirmTicketBinding.tieSeatOne.text.isNullOrBlank()
                        ) {
                            Toast.makeText(
                                requireActivity(),
                                "please fill seats",
                                Toast.LENGTH_SHORT
                            ).show()
                        } else {
                            listOfSeats = mutableListOf()
                            listOfSeats.add(
                                0,
                                SeatModel(
                                    fragmentConfirmTicketBinding.tieSeatOne.text.toString().toInt()
                                )
                            )

                            //confirm ticket
                            ticketModel = TicketModel(className, price, listOfSeats, quantity)
                            viewLifecycleOwner.lifecycleScope.launch {
                                val message = mReservationViewModel.confirmTicketRequest(
                                    userId,
                                    tripId,
                                    ticketModel
                                )
                                if (message == "success") {
                                    Toast.makeText(requireActivity(), "success", Toast.LENGTH_SHORT)
                                        .show()
                                } else {
                                    Toast.makeText(
                                        requireActivity(),
                                        "$message",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }
                        }
                    }
                }
            }
        }



        return fragmentConfirmTicketBinding.root
    }

    private fun checkQuantity(quantity: Int) {
        when {
            quantity > 5 -> {
                fragmentConfirmTicketBinding.tieSeatNumberFive.visibility = View.GONE
                fragmentConfirmTicketBinding.tieSeatNumberFour.visibility = View.GONE
                fragmentConfirmTicketBinding.tieSeatNumberThree.visibility = View.GONE
                fragmentConfirmTicketBinding.tieSeatNumberTwo.visibility = View.GONE
                Toast.makeText(requireActivity(), "Please choose less than 6", Toast.LENGTH_SHORT)
                    .show()
                fragmentConfirmTicketBinding.tieQuantity.setText("")
            }
            quantity < 1 -> {
                fragmentConfirmTicketBinding.tieSeatNumberFive.visibility = View.GONE
                fragmentConfirmTicketBinding.tieSeatNumberFour.visibility = View.GONE
                fragmentConfirmTicketBinding.tieSeatNumberThree.visibility = View.GONE
                fragmentConfirmTicketBinding.tieSeatNumberTwo.visibility = View.GONE
                Toast.makeText(requireActivity(), "Please choose more than 0", Toast.LENGTH_SHORT)
                    .show()
                fragmentConfirmTicketBinding.tieQuantity.setText("")

            }
            quantity == 5 -> {
                fragmentConfirmTicketBinding.tieSeatNumberFive.visibility = View.VISIBLE
                fragmentConfirmTicketBinding.tieSeatNumberFour.visibility = View.VISIBLE
                fragmentConfirmTicketBinding.tieSeatNumberThree.visibility = View.VISIBLE
                fragmentConfirmTicketBinding.tieSeatNumberTwo.visibility = View.VISIBLE


            }
            quantity == 4 -> {
                fragmentConfirmTicketBinding.tieSeatNumberFive.visibility = View.GONE
                fragmentConfirmTicketBinding.tieSeatNumberFour.visibility = View.VISIBLE
                fragmentConfirmTicketBinding.tieSeatNumberThree.visibility = View.VISIBLE
                fragmentConfirmTicketBinding.tieSeatNumberTwo.visibility = View.VISIBLE

            }
            quantity == 3 -> {
                fragmentConfirmTicketBinding.tieSeatNumberFive.visibility = View.GONE
                fragmentConfirmTicketBinding.tieSeatNumberFour.visibility = View.GONE
                fragmentConfirmTicketBinding.tieSeatNumberThree.visibility = View.VISIBLE
                fragmentConfirmTicketBinding.tieSeatNumberTwo.visibility = View.VISIBLE
            }
            quantity == 2 -> {
                fragmentConfirmTicketBinding.tieSeatNumberFive.visibility = View.GONE
                fragmentConfirmTicketBinding.tieSeatNumberFour.visibility = View.GONE
                fragmentConfirmTicketBinding.tieSeatNumberThree.visibility = View.GONE
                fragmentConfirmTicketBinding.tieSeatNumberTwo.visibility = View.VISIBLE
            }
            quantity == 1 -> {
                fragmentConfirmTicketBinding.tieSeatNumberFive.visibility = View.GONE
                fragmentConfirmTicketBinding.tieSeatNumberFour.visibility = View.GONE
                fragmentConfirmTicketBinding.tieSeatNumberThree.visibility = View.GONE
                fragmentConfirmTicketBinding.tieSeatNumberTwo.visibility = View.GONE
            }
        }


    }

    private fun calculatePrice(className: String, quantity: Int): Int {

        when (className) {
            "A" -> {
                val price = quantity * 200
                fragmentConfirmTicketBinding.tvPrice.setText("price: $price")
                return price
            }
            "B" -> {
                val price = quantity * 50
                fragmentConfirmTicketBinding.tvPrice.setText("price: $price")
                return price

            }
            else -> {
                val price = quantity * 10
                fragmentConfirmTicketBinding.tvPrice.setText("price: $price")
                return price

            }
        }
    }


}