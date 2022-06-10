package com.example.metroid.ui.view.main_Cycle

import android.opengl.Visibility
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.metroid.R
import com.example.metroid.databinding.FragmentReservationBinding
import com.example.metroid.databinding.FragmentSearchBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SearchFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SearchFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val fragmentSearchBinding
                : FragmentSearchBinding =
            FragmentSearchBinding.inflate(inflater)

        val firstLine = arrayListOf("New El-Marg",
            "El-Marg", "Ezbet El-Nakhl", "Ain Shams", "El-Matareyya", "Helmeyet El-Zeitoun",
            "Hadayeq El-Zeitoun", "Saray El-Qobba", "Hammamat El-Qobba", "Kobry El-Qoba",
            "Mansheyet El-Sadr", "El-Demerdash", "Ghamra", "Al-Shohadaa", "Orabi",
            "Gamal Abdel-Nasser", "El-Sadat", "Saad Zaghloul", "El-Sayeda Zainab",
            "El-Malek El-Saleh", "Mar Girgis", "El-Zahraa", "Dar El-Salam",
            "Hadayeq El-Maadi", "Maadi", "Sakanat El-Maadi", "Tora El-Balad",
            "Kozzika", "Tora El-Asmant", "El-Maasara", "Hadayeq Helwan", "Wadi Hof",
            "Helwan University", "Ain Helwan", "Helwan")

        return fragmentSearchBinding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SearchFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SearchFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}