package com.aerotech.flytix.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.aerotech.flytix.databinding.ItemListticketBinding
import com.aerotech.flytix.model.ticket.DataGetTicketItem

class ResultSearchBackAdapter (private var itemClick: ListSearchBackInterface) : RecyclerView.Adapter<ResultSearchBackAdapter.ViewHolder>(){

    private val differCallback = object : DiffUtil.ItemCallback<DataGetTicketItem>(){
        override fun areItemsTheSame(
            oldItem: DataGetTicketItem,
            newItem: DataGetTicketItem
        ): Boolean {
            return oldItem.id == oldItem.id
        }

        override fun areContentsTheSame(
            oldItem: DataGetTicketItem,
            newItem: DataGetTicketItem
        ): Boolean {
            return oldItem == newItem
        }

    }
    private val differ = AsyncListDiffer(this, differCallback)

    inner class ViewHolder(private val binding: ItemListticketBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: DataGetTicketItem) {
            binding.ticket =item
            binding.cvDestinasi.setOnClickListener{
                item.id.let { it1 -> itemClick.onItemClickBack(it1!!) }
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemListticketBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val ticket = differ.currentList[position]
        holder.bind(ticket)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    fun setData(data : List<DataGetTicketItem>){
        differ.submitList(data)
    }

    interface ListSearchBackInterface {
        fun onItemClickBack(id: Int)
    }
}