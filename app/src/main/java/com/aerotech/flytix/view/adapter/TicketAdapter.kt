package com.aerotech.flytix.view.adapter

//class TicketAdapter(private var listTicket: List<DataGetTicketItem>) :
//    RecyclerView.Adapter<TicketAdapter.ViewHolder>() {
//
//    class ViewHolder(var binding: ItemListticketBinding) : RecyclerView.ViewHolder(binding.root) {
//        fun bindTicket(itemTicket: DataGetTicketItem){
//            binding.ticket = itemTicket
//            binding.cvDestinasi.setOnClickListener{
////                val bundle = Bundle()
////                bundle.putSerializable("BUNDEL", itemFilms)
////                Navigation.findNavController(itemView).navigate(R.id.action_homeFragment_to_detailFragment, bundle)
//            }
//        }
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        val view = ItemListticketBinding.inflate(LayoutInflater.from(parent.context), parent, false)
//        return ViewHolder(view)
//    }
//
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        holder.bindTicket(listTicket[position])
////        val harga = listTicket[position].price
////        holder.binding.Harga.text = "Rp. $harga"
////        holder.binding.jenisKelas.text = listTicket[position].typeOfClass
////        holder.binding.tempatBerangkat.text = listTicket[position].airport.location
////        holder.binding.tempatTujuan.text = listTicket[position].flight.arrivalLocation
////        holder.binding.waktuBerangkat.text = listTicket[position].flight.departureTime
////        holder.binding.waktuSampai.text = listTicket[position].flight.arrivalTime
//
//    }
//
//    override fun getItemCount(): Int {
//        return listTicket.size
//    }
//}