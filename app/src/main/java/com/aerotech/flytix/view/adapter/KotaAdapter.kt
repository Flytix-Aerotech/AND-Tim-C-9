package com.aerotech.flytix.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aerotech.flytix.databinding.ItemKotaBinding
import com.aerotech.flytix.model.ticket.DataKota

class KotaAdapter(private var listKota: List<DataKota>, private val itemSelectionListener: ItemSelectionListener) :
    RecyclerView.Adapter<KotaAdapter.ViewHolder>() {

    inner class ViewHolder(var binding: ItemKotaBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindKota(itemKota: DataKota){
            binding.kota = itemKota
        }
    }

    fun setSearchList(mlist: List<DataKota>){
        this.listKota = mlist
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemKotaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindKota(listKota[position])

        holder.itemView.setOnClickListener {
            itemSelectionListener.onItemSelected(listKota[position])
        }
    }

    override fun getItemCount(): Int {
        return listKota.size
    }

    interface ItemSelectionListener {
        fun onItemSelected(selectedItem: DataKota)
    }

    fun updateData(newItemList: List<DataKota>) {
        listKota = newItemList
        notifyDataSetChanged()
    }
}