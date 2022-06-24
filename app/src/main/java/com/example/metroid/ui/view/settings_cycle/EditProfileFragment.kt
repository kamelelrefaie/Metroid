package com.example.metroid.ui.view.settings_cycle

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.metroid.databinding.FragmentEditProfileBinding
import com.example.metroid.model.remote.responses.UpdateProfileData
import com.example.metroid.ui.view.viewmodel.main_cycle.MetroViewModel
import com.example.metroid.ui.view.viewmodel.main_cycle.ReservationViewModel
import com.example.metroid.utils.Constants.checkEmail

class EditProfileFragment : Fragment() {

    private lateinit var editProfileBinding: FragmentEditProfileBinding
    private lateinit var metroViewModel: MetroViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        editProfileBinding = FragmentEditProfileBinding.inflate(inflater)
        getData()

        onEditBtnClicked()


        return editProfileBinding.root
    }

    private fun getData() {
        metroViewModel = ViewModelProvider(requireActivity())[MetroViewModel::class.java]
        try {
            metroViewModel.getTripCreditsAndCount(requireActivity())
            editProfileBinding.tvCredit.text = metroViewModel.credits.value + "$"
            editProfileBinding.tvTrip.text = metroViewModel.trips.value
        } catch (e: Exception) {

        }
    }

    private fun onEditBtnClicked() {
        editProfileBinding.btnEditProfile.setOnClickListener {
            val email = editProfileBinding.etEmail.text.toString()
            val pass = editProfileBinding.etPassword.text.toString()
            val name = editProfileBinding.etUserName.text.toString()
            val phone = editProfileBinding.etPhone.text.toString()

            if (!checkEmail(email)) {
                Toast.makeText(requireActivity(), "enter valid email", Toast.LENGTH_SHORT).show()
            } else if (email.isEmpty() || pass.isEmpty() || phone.isEmpty() || name.isEmpty()) {
                Toast.makeText(requireActivity(), "please enter valid data", Toast.LENGTH_SHORT)
                    .show()
            } else {
                updateProfileData(name, pass, phone.toInt(), email)
            }
        }
    }

    private fun updateProfileData(userName: String, pass: String, phone: Int, email: String) {
        metroViewModel.editProfileFragment(
            UpdateProfileData(userName, email, phone, pass),
            requireActivity()
        )
    }
}