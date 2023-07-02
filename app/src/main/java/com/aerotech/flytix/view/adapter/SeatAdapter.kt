//package com.aerotech.flytix.view.adapter
//
//import android.widget.BaseAdapter
//
//private inner class SeatAdapter(private val numPassengers: Int) : BaseAdapter() {
//    private val selectedSeats: MutableList<Seat> = mutableListOf()
//
//    override fun getCount(): Int {
//        return seats.size * seats[0].size
//    }
//
//    override fun getItem(position: Int): Any {
//        val row = position / seats[0].size
//        val col = position % seats[0].size
//        return seats[row][col]
//    }
//
//    override fun getItemId(position: Int): Long {
//        return position.toLong()
//    }
//
//    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
//        val seatView: Button = if (convertView == null) {
//            Button(requireContext())
//        } else {
//            convertView as Button
//        }
//
//        val seat = getItem(position) as Seat
//        seatView.isEnabled = !seat.isOccupied
//
//        if (seat.number == null || seat.row.isEmpty()) {
//            seatView.setBackgroundColor(resources.getColor(R.color.transparent))
//            seatView.setTextColor(resources.getColor(R.color.white))
//            seatView.isEnabled = false
//        } else {
//            if (seat.isOccupied) {
//                seatView.setBackgroundColor(resources.getColor(R.color.hijau))
//            } else {
//                seatView.setBackgroundColor(resources.getColor(R.color.putih))
//                seatView.setOnClickListener {
//                    if (selectedSeats.contains(seat)) {
//                        selectedSeats.remove(seat)
//                        seat.isOccupied = false
//                        seatView.setBackgroundColor(resources.getColor(R.color.putih))
//                    } else {
//                        if (selectedSeats.size >= numPassengers) {
//                            Toast.makeText(
//                                requireContext(),
//                                "You have reached the maximum number of passengers.",
//                                Toast.LENGTH_SHORT
//                            ).show()
//                            return@setOnClickListener
//                        }
//                        selectedSeats.add(seat)
//                        seat.isOccupied = true
//                        seatView.setBackgroundColor(resources.getColor(R.color.hijau))
//                    }
//
//                    // Debug: Print the selected seats
//                    for (selectedSeat in selectedSeats) {
//                        val seatNumber = selectedSeat.number?.toString() ?: ""
//                        println("Selected seat: ${selectedSeat.row}$seatNumber")
//                    }
//                }
//            }
//        }
//
//        val seatNumber = seat.number?.toString() ?: ""
//        seatView.text = "${seat.row}$seatNumber"
//
//        return seatView
//    }
//}
