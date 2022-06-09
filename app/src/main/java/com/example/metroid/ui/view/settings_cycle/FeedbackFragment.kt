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


class FeedbackFragment : Fragment() {
    private lateinit var fragmentFeedbackBinding: FragmentFeedbackBinding


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        fragmentFeedbackBinding = FragmentFeedbackBinding.inflate(inflater)
        fragmentFeedbackBinding.button.setOnClickListener {
            val rateUsDialog = RatingBarDialog(requireActivity())
            rateUsDialog.window?.setBackgroundDrawable(ColorDrawable(resources.getColor(android.R.color.transparent)))
            rateUsDialog.show()
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