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
        arrayOf(Seat('A', 1), Seat('A', 2), Seat('A', 3), Seat('A', 4), Seat('A', 5), Seat('A', 6)),
        arrayOf(Seat('B', 1), Seat('B', 2), Seat('B', 3), Seat('B', 4), Seat('B', 5), Seat('B', 6)),
        arrayOf(Seat('C', 1), Seat('C', 2), Seat('C', 3), Seat('C', 4), Seat('C', 5), Seat('C', 6)),
        arrayOf(Seat('D', 1), Seat('D', 2), Seat('D', 3), Seat('D', 4), Seat('D', 5), Seat('D', 6)),
        arrayOf(Seat('E', 1), Seat('E', 2), Seat('E', 3), Seat('E', 4), Seat('E', 5), Seat('E', 6)),
        arrayOf(Seat('F', 1), Seat('F', 2), Seat('F', 3), Seat('F', 4), Seat('F', 5), Seat('F', 6)),
        arrayOf(Seat('G', 1), Seat('G', 2), Seat('G', 3), Seat('G', 4), Seat('G', 5), Seat('G', 6)),
        arrayOf(Seat('H', 1), Seat('H', 2), Seat('H', 3), Seat('H', 4), Seat('H', 5), Seat('H', 6)),
        arrayOf(Seat('I', 1), Seat('I', 2), Seat('I', 3), Seat('I', 4), Seat('I', 5), Seat('I', 6)),
        arrayOf(Seat('J', 1), Seat('J', 2), Seat('J', 3), Seat('J', 4), Seat('J', 5), Seat('J', 6))
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
                }
            }
            return seatView
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }
}
class Seat(val row: Char, val number: Int, var isOccupied: Boolean = false)
