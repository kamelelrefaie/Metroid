package com.example.metroid.ui.view.settings_cycle

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.metroid.databinding.FragmentEditProfileBinding
import com.example.metroid.model.remote.responses.UpdateProfileData
import com.example.metroid.ui.view.viewmodel.main_cycle.MetroViewModel
import com.example.metroid.utils.Constants.checkEmail
import com.shashank.sony.fancytoastlib.FancyToast

class EditProfileFragment : Fragment() {

    private lateinit var editProfileBinding: FragmentEditProfileBinding
    private lateinit var metroViewModel: MetroViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        editProfileBinding = FragmentEditProfileBinding.inflate(inflater)
        metroViewModel = ViewModelProvider(requireActivity())[MetroViewModel::class.java]


        editProfileBinding.ivProfile.setOnClickListener {
            var intent = Intent(Intent.ACTION_PICK)
            intent.data= MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            startActivityForResult(intent,1000)
        }
        getData()
        onEditBtnClicked()


        return editProfileBinding.root
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK){
            if(requestCode == 1000){
                editProfileBinding.ivProfile.setImageURI(data?.data)
            }
        }
    }
    private fun getData() {
        try {
            metroViewModel.getTripCreditsAndCount(requireActivity())
            metroViewModel.credits.observe(requireActivity()) {
                editProfileBinding.tvCredit.text = "$it L.E"
            }

            metroViewModel.trips.observe(requireActivity()) {
                editProfileBinding.tvTrip.text = it
            }
        } catch (e: Exception) {
            Log.e("edit Profile", e.toString())
        }
    }

    private fun onEditBtnClicked() {
        editProfileBinding.btnEditProfile.setOnClickListener {
            val email = editProfileBinding.etEmail.text.toString()
            val pass = editProfileBinding.etPassword.text.toString()
            val name = editProfileBinding.etUserName.text.toString()
            val phone = editProfileBinding.etPhone.text.toString()

            if (!checkEmail(email)) {

                FancyToast.makeText(
                    requireActivity(),
                    "enter valid email",
                    FancyToast.LENGTH_LONG,
                    FancyToast.ERROR,
                    true
                ).show()

            } else if (email.isEmpty() || pass.isEmpty() || phone.isEmpty() || name.isEmpty()) {
                FancyToast.makeText(
                    requireActivity(),
                    "please enter valid data",
                    FancyToast.LENGTH_LONG,
                    FancyToast.ERROR,
                    true
                ).show()
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