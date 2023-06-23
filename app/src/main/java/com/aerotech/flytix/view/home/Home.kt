package com.aerotech.flytix.view.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.aerotech.flytix.R
import com.aerotech.flytix.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Home : Fragment(),
    Penumpang.totalPenumpangListener, Keberangkatan.DatePickerListener, Kembali.DatePickerListener,
    KelasKursi.kelasKursiListener,Darimana.getItemkotaListener, Tujuan.getItemkotaTujuanListener {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.etAsallokasi.setOnClickListener {
            val pilihLokasiAsal = Darimana()
            pilihLokasiAsal.listener = this
            pilihLokasiAsal.show(parentFragmentManager, pilihLokasiAsal.tag)
        }

        binding.etTujuanlokasi.setOnClickListener {
            val pilihLokasiTujuan = Tujuan()
            pilihLokasiTujuan.listener = this
            pilihLokasiTujuan.show(parentFragmentManager, pilihLokasiTujuan.tag)
        }

        binding.etPenumpang.setOnClickListener {
            val fragmentBottomPenumpang = Penumpang()
            fragmentBottomPenumpang.listener = this
            fragmentBottomPenumpang.show(parentFragmentManager, fragmentBottomPenumpang.tag)
        }

        binding.etKeberangkatan.setOnClickListener {
            val datePickerKeberangkatanFragment = Keberangkatan()
            datePickerKeberangkatanFragment.listener = this
            datePickerKeberangkatanFragment.show(
                parentFragmentManager,
                datePickerKeberangkatanFragment.tag
            )
        }

        binding.etKepulangan.setOnClickListener {
            val datePickerKepulanganFragment = Kembali()
            datePickerKepulanganFragment.listener = this
            datePickerKepulanganFragment.show(
                parentFragmentManager,
                datePickerKepulanganFragment.tag
            )
        }

        binding.switchPp.setOnCheckedChangeListener { _, ischecked ->
            if (ischecked) {
                binding.tvKepulangan.visibility = View.VISIBLE
                binding.ivKepulangan.visibility = View.VISIBLE
                binding.etKepulangan.visibility = View.VISIBLE

            } else {
                binding.tvKepulangan.visibility = View.GONE
                binding.ivKepulangan.visibility = View.GONE
                binding.etKepulangan.visibility = View.GONE
            }
        }

        binding.etKelaskursi.setOnClickListener {
            val pilihKelasKursi = KelasKursi()
            pilihKelasKursi.listener = this
            pilihKelasKursi.show(parentFragmentManager, pilihKelasKursi.tag)
        }

        binding.btnCaripenerbangan.setOnClickListener {
            findNavController().navigate(R.id.action_home3_to_resultSearch)
        }
    }

    override fun onDateSelectedKepulangan(date: String) {
        // Set the selected date in the EditText
        binding.etKepulangan.setText(date)
    }

    override fun totalPenumpang(total: String) {
        binding.etPenumpang.setText("$total Penumpang")
    }

    override fun onDateSelected(date: String) {
        binding.etKeberangkatan.setText(date)
    }

    override fun pilihKelasKursi(kelas: String) {
        binding.etKelaskursi.setText(kelas)
    }
//    override fun onItemSelected(selectedItem: DataGetTicketItem) {
//        val selectedAirport = "${selectedItem.airport.location} - ${selectedItem.airport.code}"
//        binding.etAsallokasi.setText(selectedAirport)
//    }

    override fun getItemKota(kota: String) {
        binding.etAsallokasi.setText(kota)
    }

    override fun getItemKotaTujuan(kota: String) {
        binding.etTujuanlokasi.setText(kota)
    }
}


