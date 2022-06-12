package com.example.metroid.ui.view.settings_cycle

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.metroid.databinding.FragmentEditProfileBinding

class EditProfileFragment: Fragment(){

    private lateinit var editProfileBinding: FragmentEditProfileBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        editProfileBinding= FragmentEditProfileBinding.inflate(inflater)

        return editProfileBinding.root
    }
}