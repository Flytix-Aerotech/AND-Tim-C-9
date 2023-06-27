package com.aerotech.flytix.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.RecyclerView
import com.aerotech.flytix.R
import com.aerotech.flytix.databinding.ItemBiodataPenumpangBinding
import com.aerotech.flytix.databinding.ItemListBinding
import com.aerotech.flytix.model.passengers.Data

class BiodataPenumpangAdapter (private var listPenumpang: List<Data>) : RecyclerView.Adapter<BiodataPenumpangAdapter.ViewHolder>() {
    class ViewHolder (var binding: ItemBiodataPenumpangBinding) : RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemBiodataPenumpangBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listPenumpang.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val items = listOf("Option 1", "Option 2", "Option 3", "Option 4")
        val adapter = ArrayAdapter(holder.binding.root.context, R.layout.item_list, items)
        holder.binding.autocompletetextview.setAdapter(adapter)
    }
}