package com.aerotech.flytix.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aerotech.flytix.databinding.ItemKelaskursiBinding
import com.aerotech.flytix.model.ticket.DataKelasKursi

class KelasKursiAdapter(private var listKelas: List<DataKelasKursi>, private val itemSelectionListener: ItemSelectionListener) :
    RecyclerView.Adapter<KelasKursiAdapter.ViewHolder>() {

    inner class ViewHolder(var binding: ItemKelaskursiBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindKota(itemKelas: DataKelasKursi){
            binding.kelas = itemKelas
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemKelaskursiBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindKota(listKelas[position])
        holder.itemView.setOnClickListener {
            itemSelectionListener.onItemSelected(listKelas[position])
        }
    }

    override fun getItemCount(): Int {
        return listKelas.size
    }

    interface ItemSelectionListener {
        fun onItemSelected(selectedItem: DataKelasKursi)
    }

}