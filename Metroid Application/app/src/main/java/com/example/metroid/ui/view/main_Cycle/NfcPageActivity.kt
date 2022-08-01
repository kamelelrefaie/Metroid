package com.example.metroid.ui.view.main_Cycle

import android.app.PendingIntent
import android.content.Intent
import android.nfc.NdefMessage
import android.nfc.NfcAdapter
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.metroid.R
import com.example.metroid.databinding.ActivityNfcPageBinding
import com.example.metroid.ui.view.viewmodel.main_cycle.MetroViewModel
import com.example.metroid.utils.Constants.getFirstLine
import com.example.metroid.utils.Constants.getSecondLine
import com.example.metroid.utils.Constants.getThirdLine
import com.example.metroid.utils.parser.NdefMessageParser
import com.shashank.sony.fancytoastlib.FancyToast
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.abs


@AndroidEntryPoint
class NfcPageActivity : AppCompatActivity() {
    private var counter = 2
    private lateinit var activityNfcPageBinding: ActivityNfcPageBinding
    private var mNfcAdapter: NfcAdapter? = null
    private var mPendingIntent: PendingIntent? = null
    var firstStationName = ""
    var firstStationKey = ""
    var secondStationName = ""
    var secondStationKey = ""

    private val aSadatKey = 17
    private val aShohadaKey = 14

    private val bAttaba = 9
    private val cAttaaba = 1

    private val bSadatKey = 11
    private val bShohadaKey = 8
   private lateinit var  viewModel:MetroViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityNfcPageBinding = ActivityNfcPageBinding.inflate(layoutInflater)
        setContentView(activityNfcPageBinding.root)
        mNfcAdapter = NfcAdapter.getDefaultAdapter(this)

        viewModel = ViewModelProvider(this)[MetroViewModel::class.java]


        if (checkNFCEnable()) {
            mPendingIntent = PendingIntent.getActivity(
                this, 0,
                Intent(this, this.javaClass)
                    .addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), PendingIntent.FLAG_MUTABLE
            )
        } else {
            activityNfcPageBinding.tvFrom.text = getString(R.string.tv_noNfc)
        }

    }


    override fun onResume() {
        super.onResume()
        try {
            mNfcAdapter?.enableForegroundDispatch(this, mPendingIntent, null, null)
        }catch (e:Exception){
            Log.e("nfc",e.toString())
        }
    }


    override fun onPause() {
        super.onPause()
        mNfcAdapter?.disableForegroundDispatch(this)
    }


    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        if (NfcAdapter.ACTION_NDEF_DISCOVERED == intent.action) {
            intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES)?.also { rawMessages ->
                val messages: List<NdefMessage> = rawMessages.map { it as NdefMessage }
                // Process the messages array.
                parserNDEFMessage(messages)
            }
        }
    }

    private fun parserNDEFMessage(messages: List<NdefMessage>) {
        val builder = StringBuilder()
        val records = NdefMessageParser.parse(messages[0])
        val size = records.size

        for (i in 0 until size) {
            val record = records.get(i)
            val str = record.str()
            builder.append(str).append("\n")
        }

        if (counter == 2) {
            activityNfcPageBinding.tvWelcome.text = "Touch the machine to exit"
            activityNfcPageBinding.ll.visibility = View.VISIBLE

            //get first station key
            firstStationKey = builder.toString().trim()

            //set Station Name
            firstStationName = getStationName(builder)
            activityNfcPageBinding.tvFrom.text = firstStationName

            counter--
        } else if (counter == 1) {
            activityNfcPageBinding.tvWelcome.text = "Return To MainMenu"
            activityNfcPageBinding.tvPriceEditable.visibility = View.VISIBLE

            //get second station key
            secondStationKey = builder.toString().trim()

            //set station name
            secondStationName = getStationName(builder)
            activityNfcPageBinding.tvTo.text = secondStationName
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

            activityNfcPageBinding.tvPriceEditable.text = "$price L.E"
            counter--

            //send data to server
            try {
                viewModel.postTrip(price,firstStationName,secondStationName,this)

            }catch (e:Exception){
                Log.e("viewmodel","$e")
            }

        } else if (counter == 0) {
            FancyToast.makeText(
                this,
                "please, go back to main menu",
                FancyToast.LENGTH_LONG,
                FancyToast.INFO,
                true
            ).show()
        }

    }


    private fun checkNFCEnable(): Boolean {
        return if (mNfcAdapter == null) {
            activityNfcPageBinding.tvFrom.text = getString(R.string.tv_noNfc)
            false
        } else {
            mNfcAdapter!!.isEnabled
        }
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