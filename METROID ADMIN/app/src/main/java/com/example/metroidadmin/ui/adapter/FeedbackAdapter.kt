package com.example.metroidadmin.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.metroidadmin.databinding.ItemFeedbackBinding
import com.example.metroidadmin.remote.responses.FeedbacResponeItem
import kotlinx.android.synthetic.main.item_feedback.view.*

class FeedbackAdapter() : RecyclerView.Adapter<FeedbackAdapter.FeedbackViewHolder>() {

    private var feedbacResponeItem = ArrayList<FeedbacResponeItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedbackViewHolder {
        val binding: ItemFeedbackBinding =
            ItemFeedbackBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return FeedbackViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FeedbackViewHolder, position: Int) {
        holder.itemView.subject.text = "Subject : \n ${feedbacResponeItem.get(position).subject}"
        holder.itemView.content.text = "Content : \n ${feedbacResponeItem.get(position).content}"
        holder.itemView.rate.text = "Rate : \n ${feedbacResponeItem.get(position).rate}"

    }

    override fun getItemCount(): Int {
        return feedbacResponeItem.size
    }

    fun setList(feedbacResponeItem: ArrayList<FeedbacResponeItem>) {
        this.feedbacResponeItem = feedbacResponeItem
        notifyDataSetChanged()
    }

    class FeedbackViewHolder(view: ItemFeedbackBinding) : RecyclerView.ViewHolder(view.root)
}