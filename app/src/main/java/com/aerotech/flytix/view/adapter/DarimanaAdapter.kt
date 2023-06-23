package com.aerotech.flytix.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aerotech.flytix.databinding.ItemListairportBinding
import com.aerotech.flytix.model.ticket.DataGetTicketItem

class DarimanaAdapter(private var listCodeDarimana: List<DataGetTicketItem>, private val itemSelectionListener: ItemSelectionListener) :
    RecyclerView.Adapter<DarimanaAdapter.ViewHolder>() {

    inner class ViewHolder(var binding: ItemListairportBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindTicket(itemTicket: DataGetTicketItem){
            binding.airport = itemTicket
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemListairportBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindTicket(listCodeDarimana[position])

        holder.itemView.setOnClickListener {
            itemSelectionListener.onItemSelected(listCodeDarimana[position])
        }
    }

    override fun getItemCount(): Int {
        return listCodeDarimana.size
    }

    interface ItemSelectionListener {
        fun onItemSelected(selectedItem: DataGetTicketItem)
    }

    fun updateData(newItemList: List<DataGetTicketItem>) {
        listCodeDarimana = newItemList
        notifyDataSetChanged()
    }
}