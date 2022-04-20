package com.example.metroid.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.example.metroid.data.StationData
import com.example.metroid.databinding.ItemReservationBinding
import com.example.metroid.databinding.ItemSettingsBinding
import kotlinx.android.synthetic.main.item_reservation.view.*
import kotlinx.android.synthetic.main.item_settings.view.*

class ReservationAdapter (private val glide: RequestManager?=null): RecyclerView.Adapter<ReservationAdapter.ReservationViewHolder>() {
    private var stationList = listOf<StationData>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReservationViewHolder {
        val binding: ItemReservationBinding =
            ItemReservationBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ReservationViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ReservationViewHolder, position: Int) {
        glide?.load( stationList[position].stationImage)?.into( holder.itemView.ivStation)
         holder.itemView.tvStationName.text=stationList[position].stationInfo
    }

    override fun getItemCount(): Int {
        return stationList.size
    }

    fun setStationList(stationData: List<StationData>) {
        stationList = stationData
        notifyDataSetChanged()
    }

    class ReservationViewHolder(view: ItemReservationBinding) : RecyclerView.ViewHolder(view.root)

}