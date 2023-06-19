package com.aerotech.flytix.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aerotech.flytix.databinding.ItemTicketBinding
import com.aerotech.flytix.model.TicketX

class TicketAdapter(private var listTicket: List<TicketX>) :
    RecyclerView.Adapter<TicketAdapter.ViewHolder>() {

    class ViewHolder(var binding: ItemTicketBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemTicketBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val harga = listTicket[position].price.toString()
        holder.binding.Harga.text = "Rp. $harga"
        holder.binding.jenisKelas.text = listTicket[position].typeOfClass
//        holder.binding.tempatBerangkat.text = listTicket[position].airport.location
//        holder.binding.tempatTujuan.text = listTicket[position].flight.arrivalLocation
//        holder.binding.waktuBerangkat.text = listTicket[position].flight.departureTime
//        holder.binding.waktuSampai.text = listTicket[position].flight.arrivalTime

    }

    override fun getItemCount(): Int {
        return listTicket.size
    }
}