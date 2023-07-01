package com.aerotech.flytix.view.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import androidx.fragment.app.Fragment
import com.aerotech.flytix.R
import com.aerotech.flytix.databinding.FragmentKursiBinding


class Kursi : Fragment() {

    private var _binding: FragmentKursiBinding? = null
    private val binding get() = _binding!!

    private lateinit var seatAdapter: SeatAdapter

    private val seats: Array<Array<Seat>> = arrayOf(
        arrayOf(Seat(1, "A"), Seat(1, "B"), Seat(1, "C"), Seat(1, "") , Seat(1, "D"), Seat(1, "E"), Seat(1, "F")),
        arrayOf(Seat(2, "A"), Seat(2, "B"), Seat(2, "C"), Seat(2, "") , Seat(2, "D"), Seat(2, "E"), Seat(2, "F")),
        arrayOf(Seat(3, "A"), Seat(3, "B"), Seat(3, "C"), Seat(3, "") , Seat(3, "D"), Seat(3, "E"), Seat(3, "F")),
        arrayOf(Seat(4, "A"), Seat(4, "B"), Seat(4, "C"), Seat(4, "") , Seat(4, "D"), Seat(4, "E"), Seat(4, "F")),
        arrayOf(Seat(5, "A"), Seat(5, "B"), Seat(5, "C"), Seat(5, "") , Seat(5, "D"), Seat(5, "E"), Seat(5, "F")),
        arrayOf(Seat(6, "A"), Seat(6, "B"), Seat(6, "C"), Seat(6, "") , Seat(6, "D"), Seat(6, "E"), Seat(6, "F")),
        arrayOf(Seat(7, "A"), Seat(7, "B"), Seat(7, "C"), Seat(7, "") , Seat(7, "D"), Seat(7, "E"), Seat(7, "F")),
        arrayOf(Seat(8, "A"), Seat(8, "B"), Seat(8, "C"), Seat(8, "") , Seat(8, "D"), Seat(8, "E"), Seat(8, "F")),
        arrayOf(Seat(9, "A"), Seat(9, "B"), Seat(9, "C"), Seat(9, "") , Seat(9, "D"), Seat(9, "E"), Seat(9, "F")),
        arrayOf(Seat(10, "A"), Seat(10, "B"), Seat(10, "C"), Seat(10, "") , Seat(10, "D"), Seat(10, "E"), Seat(10, "F"))
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentKursiBinding.inflate(inflater, container, false)
        val rootView = binding.root

        seatAdapter = SeatAdapter()
        binding.gridViewSeats.adapter = seatAdapter

        return rootView
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private inner class SeatAdapter : BaseAdapter() {
        override fun getCount(): Int {
            return seats.size * seats[0].size
        }

        override fun getItem(position: Int): Any {
            val row = position / seats[0].size
            val col = position % seats[0].size
            return seats[row][col]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val seatView: Button = if (convertView == null) {
                Button(requireContext())
            } else {
                convertView as Button
            }

            val seat = getItem(position) as Seat
            seatView.text = "${seat.row}${seat.number}"
            seatView.isEnabled = !seat.isOccupied

            if (seat.row.isEmpty()) {
                seatView.setBackgroundColor(resources.getColor(R.color.transparent))
                seatView.setTextColor(resources.getColor(R.color.white))
                seatView.isEnabled = false
            } else {
                if (seat.isOccupied) {
                    seatView.setBackgroundColor(resources.getColor(R.color.hijau))
                } else {
                    seatView.setBackgroundColor(resources.getColor(R.color.putih))
                    seatView.setOnClickListener {
                        if (seat.isOccupied) {
                            seat.isOccupied = false
                            seatView.setBackgroundColor(resources.getColor(R.color.putih))
                        } else {
                            seat.isOccupied = true
                            seatView.setBackgroundColor(resources.getColor(R.color.hijau))
                        }

                        // Debug: Cetak data kursi yang diklik
                        println("Kursi ${seat.row}${seat.number} diklik. Status: ${seat.isOccupied}")

                    }
                }
            }

            return seatView
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }
}
class Seat(val number: Int, val row: String, var isOccupied: Boolean = false)
