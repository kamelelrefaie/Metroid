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
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.metroid.R
import com.example.metroid.databinding.FragmentConfirmTicketBinding
import com.example.metroid.model.remote.responses.SeatModel
import com.example.metroid.model.remote.responses.TicketInfoData
import com.example.metroid.model.remote.responses.TicketModel

import com.example.metroid.ui.view.viewmodel.main_cycle.ReservationViewModel
import com.example.metroid.utils.Constants
import com.shashank.sony.fancytoastlib.FancyToast
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


class ConfirmTicketFragment : Fragment() {
    private val args by navArgs<ConfirmTicketFragmentArgs>()
    private lateinit var mReservationViewModel: ReservationViewModel
    private lateinit var fragmentConfirmTicketBinding: FragmentConfirmTicketBinding
    private lateinit var listOfSeats: MutableList<SeatModel>
    private lateinit var ticketModel: TicketModel
    private lateinit var ticketInfoData: TicketInfoData
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment


        fragmentConfirmTicketBinding = FragmentConfirmTicketBinding.inflate(inflater)

        mReservationViewModel =
            ViewModelProvider(requireActivity())[ReservationViewModel::class.java]

        viewLifecycleOwner.lifecycleScope.launch {
            ticketInfoData = mReservationViewModel.getTicketInfo(userId = args.userId)
        }

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
                FancyToast.makeText(
                    requireActivity(),
                    "fill with appropriate data",
                    FancyToast.LENGTH_LONG,
                    FancyToast.ERROR,
                    true
                ).show()

            } else if (ticketInfoData.message == 1) {
                FancyToast.makeText(
                    requireActivity(),
                    "you already have a ticket",
                    FancyToast.LENGTH_LONG,
                    FancyToast.WARNING,
                    true
                ).show()
            } else {
                when (quantity) {
                    5 -> {
                        if (fragmentConfirmTicketBinding.tieSeatOne.text.isNullOrBlank() ||
                            fragmentConfirmTicketBinding.tieSeatNumberTwo.text.isNullOrBlank() ||
                            fragmentConfirmTicketBinding.tieSeatNumberThree.text.isNullOrBlank() ||
                            fragmentConfirmTicketBinding.tieSeatNumberFour.text.isNullOrBlank() ||
                            fragmentConfirmTicketBinding.tieSeatNumberFive.text.isNullOrBlank()
                        ) {

                            FancyToast.makeText(
                                requireActivity(),
                                "please fill seats",
                                FancyToast.LENGTH_LONG,
                                FancyToast.WARNING,
                                true
                            ).show()
                        } else {
                            val listOfStrings = mutableListOf<String>()
                            listOfStrings.add(fragmentConfirmTicketBinding.tieSeatOne.text.toString())
                            listOfStrings.add(fragmentConfirmTicketBinding.tieSeatNumberTwo.text.toString())
                            listOfStrings.add(fragmentConfirmTicketBinding.tieSeatNumberThree.text.toString())
                            listOfStrings.add(fragmentConfirmTicketBinding.tieSeatNumberFour.text.toString())
                            listOfStrings.add(fragmentConfirmTicketBinding.tieSeatNumberFive.text.toString())

                            if (checkDuplicateUsingAdd(listOfStrings as ArrayList<String>)) {
                                FancyToast.makeText(
                                    requireActivity(),
                                    "please don't choose same seat",
                                    FancyToast.LENGTH_LONG,
                                    FancyToast.WARNING,
                                    true
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
                                        FancyToast.makeText(
                                            requireActivity(),
                                            "success",
                                            FancyToast.LENGTH_LONG,
                                            FancyToast.SUCCESS,
                                            true
                                        ).show()

                                        findNavController().navigate(ConfirmTicketFragmentDirections.actionConfirmTicketFragmentToConfirmationTicketShowFragment())
                                    } else {
                                        FancyToast.makeText(
                                            requireActivity(),
                                            message,
                                            FancyToast.LENGTH_LONG,
                                            FancyToast.WARNING,
                                            true
                                        ).show()

                                    }
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
                            FancyToast.makeText(
                                requireActivity(),
                                "please fill seats",
                                FancyToast.LENGTH_LONG,
                                FancyToast.WARNING,
                                true
                            ).show()
                        } else {
                            val listOfStrings = mutableListOf<String>()
                            listOfStrings.add(fragmentConfirmTicketBinding.tieSeatOne.text.toString())
                            listOfStrings.add(fragmentConfirmTicketBinding.tieSeatNumberTwo.text.toString())
                            listOfStrings.add(fragmentConfirmTicketBinding.tieSeatNumberThree.text.toString())
                            listOfStrings.add(fragmentConfirmTicketBinding.tieSeatNumberFour.text.toString())
                            if (checkDuplicateUsingAdd(listOfStrings as ArrayList<String>)) {
                                FancyToast.makeText(
                                    requireActivity(),
                                    "please don't choose same seat",
                                    FancyToast.LENGTH_LONG,
                                    FancyToast.WARNING,
                                    true
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
                                        FancyToast.makeText(
                                            requireActivity(),
                                            "success",
                                            FancyToast.LENGTH_LONG,
                                            FancyToast.SUCCESS,
                                            true
                                        ).show()
                                        findNavController().navigate(ConfirmTicketFragmentDirections.actionConfirmTicketFragmentToConfirmationTicketShowFragment())

                                    } else {
                                        FancyToast.makeText(
                                            requireActivity(),
                                            message,
                                            FancyToast.LENGTH_LONG,
                                            FancyToast.WARNING,
                                            true
                                        ).show()
                                    }
                                }
                            }
                        }
                    }
                    3 -> {
                        if (fragmentConfirmTicketBinding.tieSeatOne.text.isNullOrBlank() ||
                            fragmentConfirmTicketBinding.tieSeatNumberTwo.text.isNullOrBlank() ||
                            fragmentConfirmTicketBinding.tieSeatNumberThree.text.isNullOrBlank()

                        ) {
                            FancyToast.makeText(
                                requireActivity(),
                                "please fill seats",
                                FancyToast.LENGTH_LONG,
                                FancyToast.WARNING,
                                true
                            ).show()

                        } else {
                            val listOfStrings = mutableListOf<String>()
                            listOfStrings.add(fragmentConfirmTicketBinding.tieSeatOne.text.toString())
                            listOfStrings.add(fragmentConfirmTicketBinding.tieSeatNumberTwo.text.toString())
                            listOfStrings.add(fragmentConfirmTicketBinding.tieSeatNumberThree.text.toString())

                            if (checkDuplicateUsingAdd(listOfStrings as ArrayList<String>)) {
                                FancyToast.makeText(
                                    requireActivity(),
                                    "please don't choose same seat",
                                    FancyToast.LENGTH_LONG,
                                    FancyToast.WARNING,
                                    true
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
                                        FancyToast.makeText(
                                            requireActivity(),
                                            "success",
                                            FancyToast.LENGTH_LONG,
                                            FancyToast.SUCCESS,
                                            true
                                        ).show()
                                        findNavController().navigate(ConfirmTicketFragmentDirections.actionConfirmTicketFragmentToConfirmationTicketShowFragment())

                                    } else {
                                        FancyToast.makeText(
                                            requireActivity(),
                                            message,
                                            FancyToast.LENGTH_LONG,
                                            FancyToast.WARNING,
                                            true
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
                            FancyToast.makeText(
                                requireActivity(),
                                "please fill seats",
                                FancyToast.LENGTH_LONG,
                                FancyToast.WARNING,
                                true
                            ).show()
                        } else {
                            if ((fragmentConfirmTicketBinding.tieSeatOne.text.toString() ==
                                        fragmentConfirmTicketBinding.tieSeatNumberTwo.text.toString())
                            ) {
                                FancyToast.makeText(
                                    requireActivity(),
                                    "please don't choose same seat",
                                    FancyToast.LENGTH_LONG,
                                    FancyToast.WARNING,
                                    true
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
                                        FancyToast.makeText(
                                            requireActivity(),
                                            "success",
                                            FancyToast.LENGTH_LONG,
                                            FancyToast.SUCCESS,
                                            true
                                        ).show()
                                        findNavController().navigate(ConfirmTicketFragmentDirections.actionConfirmTicketFragmentToConfirmationTicketShowFragment())

                                    } else {
                                        FancyToast.makeText(
                                            requireActivity(),
                                            message,
                                            FancyToast.LENGTH_LONG,
                                            FancyToast.WARNING,
                                            true
                                        ).show()
                                    }
                                }
                            }
                        }
                    }
                    1 -> {
                        if (fragmentConfirmTicketBinding.tieSeatOne.text.isNullOrBlank()
                        ) {
                            FancyToast.makeText(
                                requireActivity(),
                                "please fill seats",
                                FancyToast.LENGTH_LONG,
                                FancyToast.WARNING,
                                true
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
                                    FancyToast.makeText(
                                        requireActivity(),
                                        "success",
                                        FancyToast.LENGTH_LONG,
                                        FancyToast.SUCCESS,
                                        true
                                    ).show()
                                    findNavController().navigate(ConfirmTicketFragmentDirections.actionConfirmTicketFragmentToConfirmationTicketShowFragment())

                                } else {
                                    FancyToast.makeText(
                                        requireActivity(),
                                        message,
                                        FancyToast.LENGTH_LONG,
                                        FancyToast.WARNING,
                                        true
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
                FancyToast.makeText(
                    requireActivity(),
                    "Please choose less than 6",
                    FancyToast.LENGTH_LONG,
                    FancyToast.INFO,
                    true
                ).show()
                fragmentConfirmTicketBinding.tieQuantity.setText("")
            }
            quantity < 1 -> {
                fragmentConfirmTicketBinding.tieSeatNumberFive.visibility = View.GONE
                fragmentConfirmTicketBinding.tieSeatNumberFour.visibility = View.GONE
                fragmentConfirmTicketBinding.tieSeatNumberThree.visibility = View.GONE
                fragmentConfirmTicketBinding.tieSeatNumberTwo.visibility = View.GONE
                FancyToast.makeText(
                    requireActivity(),
                    "Please choose less than 0",
                    FancyToast.LENGTH_LONG,
                    FancyToast.INFO,
                    true
                ).show()
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
                fragmentConfirmTicketBinding.tvPrice.text = "price: $price"
                return price
            }
            "B" -> {
                val price = quantity * 50
                fragmentConfirmTicketBinding.tvPrice.text = "price: $price"
                return price

            }
            else -> {
                val price = quantity * 10
                fragmentConfirmTicketBinding.tvPrice.text = "price: $price"
                return price

            }
        }
    }

    private fun checkDuplicateUsingAdd(input: ArrayList<String>): Boolean {
        val tempSet = HashSet<String>()
        for (str in input) {
            if (!tempSet.add(str)) {
                return true
            }
        }
        return false
    }


}