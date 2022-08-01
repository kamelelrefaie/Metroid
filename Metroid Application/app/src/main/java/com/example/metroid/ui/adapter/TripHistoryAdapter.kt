package com.example.metroid.ui.adapter

import javax.inject.Inject
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.metroid.databinding.ItemMetroTripHistoryBinding
import com.example.metroid.model.remote.responses.GetMetroTripsHistoryFromApiItem
import kotlinx.android.synthetic.main.item_metro_trip_history.view.*

class TripHistoryAdapter
@Inject
constructor(

) :
    RecyclerView.Adapter<TripHistoryAdapter.TripHistoryViewHolder>() {

    private var metroTripsGet = ArrayList<GetMetroTripsHistoryFromApiItem>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TripHistoryViewHolder {
        val binding: ItemMetroTripHistoryBinding =
            ItemMetroTripHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return TripHistoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TripHistoryViewHolder, position: Int) {
        holder.itemView.tvFromStation.text = metroTripsGet[position].fromStation
        holder.itemView.tvToStation.text = metroTripsGet[position].toStation
        holder.itemView.tvPrice.text = metroTripsGet[position].price.toString() + " LE"
        holder.itemView.tvDate.text = metroTripsGet[position].date.toString()
    }

    override fun getItemCount(): Int {
        return metroTripsGet.size
    }

    fun setList(metroTripsGet: ArrayList<GetMetroTripsHistoryFromApiItem>) {
        this.metroTripsGet = metroTripsGet
        notifyDataSetChanged()
    }

    class TripHistoryViewHolder(view: ItemMetroTripHistoryBinding) :
        RecyclerView.ViewHolder(view.root)
}


