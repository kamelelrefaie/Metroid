package com.example.metroid.ui.adapter

import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.example.metroid.databinding.ItemSettingsBinding
import com.example.metroid.utils.SettingsItemData
import kotlinx.android.synthetic.main.item_settings.view.*
import javax.inject.Inject

class SettingsAdapter @Inject constructor( val fragment: Fragment, val list: List<SettingsItemData>,private val glide: RequestManager?=null) :
    RecyclerView.Adapter<SettingsAdapter.SettingsViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SettingsViewHolder {
        val binding: ItemSettingsBinding =
            ItemSettingsBinding.inflate(fragment.layoutInflater, parent, false)

        return SettingsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SettingsViewHolder, position: Int) {
        val item = list[position]
        holder.itemView.tvItem.text = item.title
        glide?.load(item.image)?.into(holder.itemView.ivItem)

        holder.itemView.setOnClickListener {
            Toast.makeText(fragment.requireContext(), "item name ${item.title}", Toast.LENGTH_SHORT)
                .show()
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class SettingsViewHolder(view: ItemSettingsBinding) : RecyclerView.ViewHolder(view.root) {

    }

}