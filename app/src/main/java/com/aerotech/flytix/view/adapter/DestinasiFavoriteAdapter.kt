package com.aerotech.flytix.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aerotech.flytix.databinding.ItemDestinasiFavoriteBinding
import com.aerotech.flytix.model.ticket.destinasifavorite.Data

class DestinasiFavoriteAdapter(private var listDataDestinasi: List<Data>): RecyclerView.Adapter<DestinasiFavoriteAdapter.ViewHolder>() {
    class ViewHolder (var binding : ItemDestinasiFavoriteBinding):RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemDestinasiFavoriteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)    }

    override fun getItemCount(): Int {
        return listDataDestinasi.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.tvAsal.text = listDataDestinasi[position].flight.departureLocation
        holder.binding.tvTujuan.text = listDataDestinasi[position].flight.arrivalLocation
        holder.binding.tvPesawat.text = listDataDestinasi[position].flight.airline
        holder.binding.tvHarga.text = listDataDestinasi[position].price.toString()

    }
}