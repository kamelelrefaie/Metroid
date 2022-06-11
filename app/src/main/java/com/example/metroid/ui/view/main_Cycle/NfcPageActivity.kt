package com.example.metroid.ui.view.main_Cycle

import android.app.PendingIntent
import android.content.Intent
import android.nfc.NdefMessage
import android.nfc.NfcAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.example.metroid.R
import com.example.metroid.databinding.ActivityHomeBinding
import com.example.metroid.databinding.ActivityNfcPageBinding
import com.example.metroid.utils.parser.NdefMessageParser
import kotlin.math.abs

class NfcPageActivity : AppCompatActivity() {
    private var counter = 2
    private lateinit var activityNfcPageBinding: ActivityNfcPageBinding
    private var mNfcAdapter: NfcAdapter? = null
    private var mPendingIntent: PendingIntent? = null
    var first_station=0
    private val FIRST_LINE = arrayListOf("","New El-Marg","El-Marg","Ezbet El-Nakhl" ,"Ain Shams"
        ,"El-Matareyya" ,"Helmeyet El-Zeitoun" ,"Hadayeq El-Zeitoun" ,"Saray El-Qobba" ,"Hammamat El-Qobba" ,"Kobry El-Qoba" ,"Mansheyet El-Sadr"
        ,"El-Demerdash" ,"Ghamra" ,"Al-Shohadaa" ,"Orabi", "Gamal Abdel-Nasser" ,"El-Sadat" ,"Saad Zaghloul" ,"El-Sayeda Zainab" ,"El-Malek El-Saleh"
        ,"Mar Girgis" ,"El-Zahraa" ,"Dar El-Salam" ,"Hadayeq El-Maadi" ,"Maadi" ,"Sakanat El-Maadi" ,"Tora El-Balad" ,"Kozzika" ,"Tora El-Asmant"
        ,"El-Maasara" ,"Hadayeq Helwan" ,"Wadi Hof" ,"Helwan University" ,"Ain Helwan" ,"Helwan")

    private val firstLineT= arrayListOf(17,19)

    private val SECOND_LINE = arrayListOf("","Shubra El-Kheima","Kolleyet El-Zeraa" ,"El-Mazallat" ,"El-Khalafawy"
        , "St. Teresa" ,"Rod El-Farag" ,"Massara" ,"Al-Shodaa" ,"Attaba" ,"Mohamed Naguib" ,"El-Sadat" ,"Opera"
        ,"Dokki" ,"El-Bohooth" ,"Cairo University" ,"Faisal" ,"Giza" ,"Om El-Masryeen", "Sakyat Mekki" ,"El-Mounib")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityNfcPageBinding = ActivityNfcPageBinding.inflate(layoutInflater)
        setContentView(activityNfcPageBinding.root)
        mNfcAdapter = NfcAdapter.getDefaultAdapter(this)

        if (checkNFCEnable()) {
            mPendingIntent = PendingIntent.getActivity(
                this, 0,
                Intent(this, this.javaClass)
                    .addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP),PendingIntent.FLAG_MUTABLE
            )
        } else {
            activityNfcPageBinding.tvFrom.text = getString(R.string.tv_noNfc)
        }

    }


    override fun onResume() {
        super.onResume()
        mNfcAdapter?.enableForegroundDispatch(this, mPendingIntent, null, null)
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
            activityNfcPageBinding.tvWelcome.setText("Touch the machine to exit")
            activityNfcPageBinding.ll.visibility = View.VISIBLE
           activityNfcPageBinding.tvFrom.text = FIRST_LINE[builder.toString().trim().toInt()]
            first_station = builder.toString().trim().toInt()
            counter--
        } else if (counter == 1) {

            activityNfcPageBinding.tvWelcome.setText("Return To MainMenu")

            activityNfcPageBinding.tvTo.text = FIRST_LINE[builder.toString().trim().toInt()]

            activityNfcPageBinding.tvPriceEditable.visibility = View.VISIBLE

         val diff= abs(  first_station-  builder.toString().trim().toInt())
            Toast.makeText(this, "${diff}", Toast.LENGTH_SHORT).show()
            //Calculate Price
            var price = 5
            if (diff in 10..16) price = 7
            else if(diff > 16) price =10

            activityNfcPageBinding.tvPriceEditable.text = "$price L.E"
            counter--
        }else if (counter == 0){
            Toast.makeText(this, "plz go back to main menu ", Toast.LENGTH_SHORT).show()
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


    private fun fromSameToSame(from: Int , to: Int): Int{
        return abs(  from - to)
    }




}