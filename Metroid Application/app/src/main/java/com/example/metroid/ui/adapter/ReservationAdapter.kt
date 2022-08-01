package com.example.metroid.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.example.metroid.R

import com.example.metroid.databinding.ItemTrainBinding
import com.example.metroid.model.remote.responses.TripResponseItem
import com.example.metroid.repository.DataStoreManager
import com.example.metroid.ui.view.main_Cycle.train.TrainsFragmentDirections
import kotlinx.android.synthetic.main.item_reservation.view.ivStation
import kotlinx.android.synthetic.main.item_train.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

class ReservationAdapter @Inject constructor(
    private val glide: RequestManager? = null,
    private val userId: Long,
    private val fragment: Fragment
) :
    RecyclerView.Adapter<ReservationAdapter.ReservationViewHolder>() {
    private var trainTimeList = ArrayList<TripResponseItem>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReservationViewHolder {
        val binding: ItemTrainBinding =
            ItemTrainBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ReservationViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ReservationViewHolder, position: Int) {
        glide?.load(R.drawable.ramses_image)?.into(holder.itemView.ivStation)
        holder.itemView.tvArrTime.text = trainTimeList[position].arr
        holder.itemView.tvDeptTime.text = trainTimeList[position].dept
        val tripId = trainTimeList[position].id.toLong()

        holder.itemView.setOnClickListener {
            fragment.findNavController().navigate(
                TrainsFragmentDirections.actionTrainsFragmentToConfirmTicketFragment(
                    tripId,
                    userId
                )
            )
        }

    }


    override fun getItemCount(): Int {
        return trainTimeList.size
    }

    fun setTrainList(trainTimeList: ArrayList<TripResponseItem>) {
        this.trainTimeList = trainTimeList
        notifyDataSetChanged()
    }

    class ReservationViewHolder(view: ItemTrainBinding) : RecyclerView.ViewHolder(view.root)

}