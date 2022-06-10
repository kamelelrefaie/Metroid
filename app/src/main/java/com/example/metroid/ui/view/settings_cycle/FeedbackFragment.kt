package com.example.metroid.ui.view.settings_cycle

import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.metroid.databinding.FragmentCancelTicketBinding
import com.example.metroid.databinding.FragmentFeedbackBinding
import com.example.metroid.model.remote.responses.FeedBackRequest
import com.example.metroid.model.remote.responses.Login
import com.example.metroid.ui.view.viewmodel.main_cycle.ReservationViewModel
import com.example.metroid.ui.view.viewmodel.settings_cycle.SettingsViewModel
import kotlinx.coroutines.launch
import java.lang.Exception


class FeedbackFragment : Fragment() {
    private lateinit var fragmentFeedbackBinding: FragmentFeedbackBinding
    private lateinit var mSettingsViewModel: SettingsViewModel

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        fragmentFeedbackBinding = FragmentFeedbackBinding.inflate(inflater)

        mSettingsViewModel =
            ViewModelProvider(requireActivity())[SettingsViewModel::class.java]

        fragmentFeedbackBinding.btnSubmit.setOnClickListener {
            if (fragmentFeedbackBinding.edContent.text.toString()
                    .isEmpty() || fragmentFeedbackBinding.edSubject.text.toString().isEmpty()
            ) {
                Toast.makeText(
                    requireActivity(),
                    "Please fill content and subject",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                val content = fragmentFeedbackBinding.edContent.text.toString()
                val subject = fragmentFeedbackBinding.edSubject.text.toString()


                var userId = -1L


                val rateUsDialog = RatingBarDialog(requireActivity(), content, subject)
                rateUsDialog.window?.setBackgroundDrawable(
                    ColorDrawable(
                        resources.getColor(
                            android.R.color.transparent
                        )
                    )
                )
                rateUsDialog.show()
                var msg = Login("")
                rateUsDialog.dialogRateUsBinding.btnRate.setOnClickListener {

                    try {


                        viewLifecycleOwner.lifecycleScope.launch {
                            val userData = mSettingsViewModel.getPrefData(requireActivity())
                            mSettingsViewModel.submitFeedback(
                                FeedBackRequest(
                                    subject,
                                    content,
                                    rateUsDialog.userRate,
                                    userData.id
                                )
                            )
                            msg = mSettingsViewModel.message.value!!
                        }
                        Toast.makeText(
                            requireActivity(),
                            "Your feedback sent ",
                            Toast.LENGTH_SHORT
                        ).show()
                    }catch (e:Exception){
                        Toast.makeText(requireActivity(), "$e", Toast.LENGTH_SHORT).show()
                    }
                    rateUsDialog.dismiss()
                }

            }

        }



        return fragmentFeedbackBinding.root
    }

//    private fun drawLayout(draw: Boolean) {
//        if (draw) {
//            fragmentCancelTicketBinding.ly.visibility = View.VISIBLE
//            fragmentCancelTicketBinding.ly2.visibility = View.VISIBLE
//            fragmentCancelTicketBinding.btnCancel.visibility = View.VISIBLE
//            fragmentCancelTicketBinding.included.root.visibility = View.GONE
//        } else {
//            fragmentCancelTicketBinding.ly.visibility = View.GONE
//            fragmentCancelTicketBinding.ly2.visibility = View.GONE
//            fragmentCancelTicketBinding.btnCancel.visibility = View.GONE
//            fragmentCancelTicketBinding.included.root.visibility = View.VISIBLE
//        }
//    }

}