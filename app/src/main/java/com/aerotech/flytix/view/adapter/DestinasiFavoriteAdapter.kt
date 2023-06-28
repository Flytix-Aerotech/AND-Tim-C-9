package com.aerotech.flytix.view.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.collection.arrayMapOf
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.aerotech.flytix.R
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
        var bulan = getTanggal(listDataDestinasi[position].flight.arrivalDate.subSequence(5,7).toString())

        holder.binding.tvAsal.text = listDataDestinasi[position].flight.departureLocation
        holder.binding.tvTujuan.text = listDataDestinasi[position].flight.arrivalLocation
        holder.binding.tvPesawat.text = listDataDestinasi[position].flight.airline
        holder.binding.tvTanggal.text = listDataDestinasi[position].flight.departureDate.subSequence(7,10).toString() + " - " +
                                        listDataDestinasi[position].flight.arrivalDate.subSequence(7,10).toString() + bulan +
                                        listDataDestinasi[position].flight.arrivalDate.subSequence(0, 4).toString()
        holder.binding.tvHarga.text = listDataDestinasi[position].price.toString()

        holder.itemView.setOnClickListener { view ->
            val bundle = Bundle().apply {
                putString("TanggalKeberangkatan", listDataDestinasi[position].flight.departureDate)
                putString("TanggalKembali", listDataDestinasi[position].flight.arrivalDate)
                putString("KotaKeberangkatan", listDataDestinasi[position].flight.departureLocation)
                putString("KotaDestinasi", listDataDestinasi[position].flight.arrivalLocation)
                putString("KelasKursi", listDataDestinasi[position].typeOfClass)
                //putString("price", listDataDestinasi[position].price)
            }

            val navController = Navigation.findNavController(view)
            navController.navigate(R.id.action_home3_to_resultSearch, bundle)
        }

    }

    fun getTanggal(bulan: String): String? {
        val arr = arrayMapOf<String, String>()
        arr["01"] = " Januari "
        arr["02"] = " Februari "
        arr["03"] = " Maret "
        arr["04"] = " April "
        arr["05"] = " Mei "
        arr["06"] = " Juni "
        arr["07"] = " Juli "
        arr["08"] = " Agustus "
        arr["09"] = " September "
        arr["10"] = " Oktober "
        arr["11"] = " November "
        arr["12"] = " Desember "

        return arr[bulan]
    }
}