package com.aerotech.flytix.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.aerotech.flytix.databinding.ItemListticketBinding
import com.aerotech.flytix.model.ticket.DataGetTicketItem

class ResultSearchAdapter (private var itemClick: ListSearchGoInterface) : RecyclerView.Adapter<ResultSearchAdapter.ViewHolder>(){

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
//            binding.tvLamaperjalanan.text = item.typeOfClass
//                var simpleDateFormat = SimpleDateFormat("E, dd LLL")
//                var departure : Date? = item.departureDate
//                var departureDate = simpleDateFormat.format(departure?.time).toString()
//
//                var arrival : Date? = item.departureDate
//                var arrivalDate = simpleDateFormat.format(arrival?.time).toString()

//                binding.tvWaktuBerangkat.text = departureDate
//                binding.tvWaktuBerangkat.text = item.flightId.departureTime
//                binding.tvWaktuSampai.text = item.flightId.arrivalTime
//                binding.tvWaktuBerangkat.text = item.flightId.departureTime
//                binding.tvWaktuBerangkat.text = item.flightId.departureTime
//
////                binding.arrivalDate.text = arrivalDate
//                binding.arrivalTime.text = item.arrivalTime.toString()
//                binding.kelas.text = item.classX.toString()
//                binding.btnKelas.text = item.classX.toString()
//                binding.price.text = item.price.toString()
//                binding.codeIataFrom.text = item.origin?.cityCode.toString()
//                binding.city1.text = item.origin?.city.toString()
//                binding.codeIataTo.text = item.destination?.cityCode.toString()
//                binding.city2.text = item.destination?.city.toString()
//                binding.company.text = item.airplane?.company?.companyName.toString()

            binding.cvDestinasi.setOnClickListener{
                item.id.let { it1 -> itemClick.onItemClickGo(it1) }
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

    interface ListSearchGoInterface {
        fun onItemClickGo(id: Int)
    }
}