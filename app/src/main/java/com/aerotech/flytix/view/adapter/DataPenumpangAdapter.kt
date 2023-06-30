package com.aerotech.flytix.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aerotech.flytix.databinding.ItemJenisPenumpanBinding
import com.dwiki.tiketku.model.penumpang.Penumpang

class DataPenumpangAdapter(private val listDewasa:List<Penumpang>):RecyclerView.Adapter<DataPenumpangAdapter.ViewHolder>() {

    private var listener:OnItemClickListener? =null

    fun setOnItemClickListener(listener: OnItemClickListener){
        this.listener = listener
    }

    interface OnItemClickListener{
        fun onItemClick(position: Int)
    }

    inner class ViewHolder (private val binding:ItemJenisPenumpanBinding):RecyclerView.ViewHolder(binding.root) {
        fun bind(penumpang: Penumpang){
            binding.tvJenisPenumpang.text = penumpang.penumpang
            binding.cvItemJenisPenumpang.setOnClickListener {
                listener?.onItemClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataPenumpangAdapter.ViewHolder {
        val binding = ItemJenisPenumpanBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DataPenumpangAdapter.ViewHolder, position: Int) {
        holder.bind(listDewasa[position])

    }

    override fun getItemCount(): Int  = listDewasa.size


}