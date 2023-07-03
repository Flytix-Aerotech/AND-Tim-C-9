package com.aerotech.flytix.view.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.aerotech.flytix.R
import com.aerotech.flytix.databinding.FragmentKursiBinding
import com.aerotech.flytix.viewmodel.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlin.properties.Delegates

@AndroidEntryPoint
class Kursi : Fragment() {

    private var _binding: FragmentKursiBinding? = null
    private val binding get() = _binding!!
    private lateinit var searchViewModel: SearchViewModel
    private lateinit var seatAdapter: SeatAdapter
    private var pilihJumlah =0 // Jumlah kursi yang dipilih
    private var selectedSeatsCount = 0 // Jumlah kursi yang telah dipilih
    var kursi by Delegates.notNull<Int>()

    private val seats: Array<Array<Seat>> = arrayOf(
        arrayOf(Seat(null, "A"), Seat(null, "B"), Seat(null, "C"), Seat(null, ""), Seat(null, "D"), Seat(null, "E"), Seat(null, "F")),
        arrayOf(Seat(1, "A"), Seat(1, "B"), Seat(1, "C"), Seat(1, ""), Seat(1, "D"), Seat(1, "E"), Seat(1, "F")),
        arrayOf(Seat(2, "A"), Seat(2, "B"), Seat(2, "C"), Seat(2, ""), Seat(2, "D"), Seat(2, "E"), Seat(2, "F")),
        arrayOf(Seat(3, "A"), Seat(3, "B"), Seat(3, "C"), Seat(3, ""), Seat(3, "D"), Seat(3, "E"), Seat(3, "F")),
        arrayOf(Seat(4, "A"), Seat(4, "B"), Seat(4, "C"), Seat(4, ""), Seat(4, "D"), Seat(4, "E"), Seat(4, "F")),
        arrayOf(Seat(5, "A"), Seat(5, "B"), Seat(5, "C"), Seat(5, ""), Seat(5, "D"), Seat(5, "E"), Seat(5, "F")),
        arrayOf(Seat(6, "A"), Seat(6, "B"), Seat(6, "C"), Seat(6, ""), Seat(6, "D"), Seat(6, "E"), Seat(6, "F")),
        arrayOf(Seat(7, "A"), Seat(7, "B"), Seat(7, "C"), Seat(7, ""), Seat(7, "D"), Seat(7, "E"), Seat(7, "F")),
        arrayOf(Seat(8, "A"), Seat(8, "B"), Seat(8, "C"), Seat(8, ""), Seat(8, "D"), Seat(8, "E"), Seat(8, "F")),
        arrayOf(Seat(9, "A"), Seat(9, "B"), Seat(9, "C"), Seat(9, ""), Seat(9, "D"), Seat(9, "E"), Seat(9, "F")),
        arrayOf(Seat(10, "A"), Seat(10, "B"), Seat(10, "C"), Seat(10, ""), Seat(10, "D"), Seat(10, "E"), Seat(10, "F"))
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        var kursii = arguments?.getString("jumlahPenumpang")!!.toInt()
        kursi = kursii
        //Log.d("jumlah Penumpang", kursi)
        _binding = FragmentKursiBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        searchViewModel = ViewModelProvider(this).get(SearchViewModel::class.java)

        seatAdapter = SeatAdapter()
        binding.gridViewSeats.adapter = seatAdapter

              binding.btnSelesai.setOnClickListener {
                    if (selectedSeatsCount == kursi) {
                        findNavController().navigate(R.id.action_kursi_to_detail)
                    } else {
                        Toast.makeText(requireContext(), "Jumlah kursi yang dipilih tidak sesuai", Toast.LENGTH_SHORT).show()
                    }
                }
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
            seatView.isEnabled = !seat.isOccupied

            if (seat.number == null || seat.row.isEmpty()) {
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
                            selectedSeatsCount--
                        } else {
                            if (selectedSeatsCount < kursi) {
                                seat.isOccupied = true
                                seatView.setBackgroundColor(resources.getColor(R.color.hijau))
                                selectedSeatsCount++
                            } else {
                                Toast.makeText(requireContext(), "Anda hanya dapat memilih $kursi kursi", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                }
            }

            val seatNumber = seat.number?.toString() ?: ""
            seatView.text = "${seat.row}${seatNumber}"

            return seatView
        }
    }

    data class Seat(val number: Int?, val row: String, var isOccupied: Boolean = false)
}
