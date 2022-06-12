package com.example.metroid.ui.view.main_Cycle

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.metroid.R
import com.example.metroid.databinding.FragmentSearchBinding
import com.example.metroid.utils.Constants
import com.example.metroid.utils.Constants.FIRST_LINE
import com.example.metroid.utils.Constants.METRO_LINES
import com.example.metroid.utils.Constants.SECOND_LINE
import com.example.metroid.utils.Constants.THIRD_LINE
import com.example.metroid.utils.Constants.getFirstLine
import com.example.metroid.utils.Constants.getSecondLine
import com.example.metroid.utils.Constants.getThirdLine
import kotlinx.android.synthetic.main.fragment_search.*
import kotlin.math.abs


class SearchFragment : Fragment() {
    private val aSadatKey = 17
    private val aShohadaKey = 14

    private val bAttaba = 9
    private val cAttaaba = 1

    private val bSadatKey = 11
    private val bShohadaKey = 8

    var firstStationKey =""
    var secondStationKey=""

    private lateinit var fragmentSearchBinding: FragmentSearchBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentSearchBinding = FragmentSearchBinding.inflate(inflater)

        val firstLine = FIRST_LINE.drop(1)
        val secondLine = SECOND_LINE.drop(1)
        val thirdLine = THIRD_LINE.drop(1)

      

        val metroLineAdapter = ArrayAdapter(
            requireActivity(),
            R.layout.item_list_reservation,
            METRO_LINES
        )

        val metroFirstLineAdapter = ArrayAdapter(
            requireActivity(),
            R.layout.item_list_reservation,
            firstLine
        )

        val metroSecondLineAdapter = ArrayAdapter(
            requireActivity(),
            R.layout.item_list_reservation,
            secondLine
        )

        val metroThirdLineAdapter = ArrayAdapter(
            requireActivity(),
            R.layout.item_list_reservation,
            thirdLine
        )

        fragmentSearchBinding.actMetroLineFrom.setAdapter(metroLineAdapter)
        fragmentSearchBinding.actMetroLineTo.setAdapter(metroLineAdapter)


        // from
        fragmentSearchBinding.actMetroLineFrom.setOnItemClickListener { adapterView, view, position, id ->


            when (position) {
                0 -> {
                    fragmentSearchBinding.actFrom.setAdapter(metroFirstLineAdapter)
                    fragmentSearchBinding.actFrom.setOnItemClickListener { adapterView, view, position, id ->
                       firstStationKey = "a${position+1}"
                    }
                    }
                1 -> {
                    fragmentSearchBinding.actFrom.setAdapter(metroSecondLineAdapter)
                    fragmentSearchBinding.actFrom.setOnItemClickListener { adapterView, view, position, id ->
                        firstStationKey = "b${position+1}"
                    }
                }
                2 -> {
                    fragmentSearchBinding.actFrom.setAdapter(metroThirdLineAdapter)
                    fragmentSearchBinding.actFrom.setOnItemClickListener { adapterView, view, position, id ->
                        firstStationKey = "c${position+1}"
                    }

                }
            }
        }

        // to
        fragmentSearchBinding.actMetroLineTo.setOnItemClickListener { adapterView, view, position, id ->


            when (position) {
                0 -> {
                    fragmentSearchBinding.actTo.setAdapter(metroFirstLineAdapter)
                    fragmentSearchBinding.actTo.setOnItemClickListener { adapterView, view, position, id ->
                        secondStationKey = "a${position+1}"
                    }
                }
                1 -> {
                    fragmentSearchBinding.actTo.setAdapter(metroSecondLineAdapter)
                    fragmentSearchBinding.actTo.setOnItemClickListener { adapterView, view, position, id ->
                        secondStationKey = "b${position+1}"
                    }
                }
                2 -> {
                    fragmentSearchBinding.actTo.setAdapter(metroThirdLineAdapter)
                    fragmentSearchBinding.actTo.setOnItemClickListener { adapterView, view, position, id ->
                        secondStationKey = "c${position+1}"
                    }

                }
            }
        }



        fragmentSearchBinding.btnSearch.setOnClickListener {
            if (fragmentSearchBinding.actFrom.text.isEmpty() || fragmentSearchBinding.actTo.text.isEmpty()){
                Toast.makeText(requireActivity(), "Please, choose right way", Toast.LENGTH_SHORT).show()
            }else{
                var diff = 1
                if (firstStationKey[0] == secondStationKey[0]) {
                    diff = fromSameToSame(
                        firstStationKey.drop(1).toInt(),
                        secondStationKey.drop(1).toInt()
                    )
                } else if (firstStationKey[0] == 'a' && secondStationKey[0] == 'b') {
                    diff = calcDiffFromAtoB()
                } else if (firstStationKey[0] == 'b' && secondStationKey[0] == 'a') {
                    diff = calcDiffFromBtoA()
                } else if (firstStationKey[0] == 'b' && secondStationKey[0] == 'c') {
                    diff = calcDiffFromBtoC()
                } else if (firstStationKey[0] == 'c' && secondStationKey[0] == 'b') {
                    diff = calcDiffFromCtoB()
                } else if (firstStationKey[0] == 'c' && secondStationKey[0] == 'a') {
                    diff = calcDiffFromCtoA()
                } else if (firstStationKey[0] == 'a' && secondStationKey[0] == 'c') {
                    diff = calcDiffFromAtoC()
                }
                //Calculate Price
                var price = 5
                if (diff in 10..16) price = 7
                else if (diff > 16) price = 10
                fragmentSearchBinding.tvPrice.text = "${price} L.E"

                fragmentSearchBinding.tvTo.text = fragmentSearchBinding.actTo.text
                fragmentSearchBinding.tvFrom.text = fragmentSearchBinding.actFrom.text

            }

        }

        return fragmentSearchBinding.root
    }



    private fun fromSameToSame(from: Int, to: Int): Int {
        return abs(from - to)
    }

    private fun getStationName(stringBuilder: StringBuilder): String {

        return if (stringBuilder.toString().trim()[0] == 'a') {
            val firstLine = getFirstLine()
            firstLine[stringBuilder.toString().trim()]!!
        } else if (stringBuilder.toString().trim()[0] == 'b') {
            val secondLine = getSecondLine()
            secondLine[stringBuilder.toString().trim()]!!
        } else if (stringBuilder.toString().trim()[0] == 'c') {
            val thirdLine = getThirdLine()
            thirdLine[stringBuilder.toString().trim()]!!
        } else {
            ""
        }


    }

    private fun calcDiffFromAtoB(): Int {
        var sadatPath = 0
        var shohadaPath = 0
        //sadat path
        sadatPath = fromSameToSame(firstStationKey.drop(1).toInt(), aSadatKey)
        sadatPath += fromSameToSame(secondStationKey.drop(1).toInt(), bSadatKey)

        //shouhadaPath
        shohadaPath = fromSameToSame(firstStationKey.drop(1).toInt(), aShohadaKey)
        shohadaPath += fromSameToSame(secondStationKey.drop(1).toInt(), bShohadaKey)

        return if (shohadaPath <= sadatPath) {
            sadatPath
        } else {
            shohadaPath
        }
    }

    private fun calcDiffFromBtoA(): Int {
        var sadatPath = 0
        var shohadaPath = 0
        //sadat path
        sadatPath = fromSameToSame(firstStationKey.drop(1).toInt(), bSadatKey)
        sadatPath += fromSameToSame(secondStationKey.drop(1).toInt(), aSadatKey)

        //shouhadaPath
        shohadaPath = fromSameToSame(firstStationKey.drop(1).toInt(), bShohadaKey)
        shohadaPath += fromSameToSame(secondStationKey.drop(1).toInt(), aShohadaKey)

        return if (shohadaPath <= sadatPath) {
            sadatPath
        } else {
            shohadaPath
        }
    }

    private fun calcDiffFromBtoC(): Int {

        //attaba path
        var attaba: Int = fromSameToSame(firstStationKey.drop(1).toInt(), bAttaba)
        attaba += fromSameToSame(secondStationKey.drop(1).toInt(), cAttaaba)

        return attaba
    }

    private fun calcDiffFromCtoB(): Int {

        //attaba path
        var attaba: Int = fromSameToSame(firstStationKey.drop(1).toInt(), cAttaaba)
        attaba += fromSameToSame(secondStationKey.drop(1).toInt(), bAttaba)

        return attaba
    }

    private fun calcDiffFromCtoA(): Int {

        //attaba path
        var attaba: Int = fromSameToSame(firstStationKey.drop(1).toInt(), cAttaaba)

        if (16 <= secondStationKey.drop(1).toInt()) {
            attaba += fromSameToSame(bSadatKey, bAttaba)
            attaba += fromSameToSame(secondStationKey.drop(1).toInt(), aSadatKey)

        } else {
            attaba += fromSameToSame(bShohadaKey, bAttaba)
            attaba += fromSameToSame(secondStationKey.drop(1).toInt(), aShohadaKey)
        }


        return attaba
    }

    private fun calcDiffFromAtoC(): Int {

        //attaba path
        var attaba: Int = 0

        if (16 <= firstStationKey.drop(1).toInt()) {
            attaba += fromSameToSame(aSadatKey, firstStationKey.drop(1).toInt())
            attaba += fromSameToSame(bAttaba, bSadatKey)

        } else {
            attaba += fromSameToSame(aShohadaKey, firstStationKey.drop(1).toInt())
            attaba += fromSameToSame(bAttaba, bShohadaKey)
        }
        attaba += fromSameToSame(cAttaaba, secondStationKey.drop(1).toInt())

        return attaba
    }

}