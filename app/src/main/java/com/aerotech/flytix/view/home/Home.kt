package com.aerotech.flytix.view.home

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.aerotech.flytix.R
import com.aerotech.flytix.databinding.FragmentHomeBinding
import com.aerotech.flytix.viewmodel.SearchViewModel
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.DateValidatorPointForward
import com.google.android.material.datepicker.MaterialDatePicker
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@AndroidEntryPoint
class Home : Fragment(),
    Penumpang.totalPenumpangListener, KelasKursi.kelasKursiListener, Darimana.getItemkotaListener,
    Tujuan.getItemkotaTujuanListener {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var searchViewModel: SearchViewModel
    private lateinit var bund: Bundle

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        searchViewModel = ViewModelProvider(this)[SearchViewModel::class.java]
        bund = Bundle()
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
//            val datePickerKeberangkatanFragment = Keberangkatan()
//            datePickerKeberangkatanFragment.listener = this
//            datePickerKeberangkatanFragment.show(
//                parentFragmentManager,
//                datePickerKeberangkatanFragment.tag
//            )
            tripOneway()
        }


        binding.etKepulangan.setOnClickListener {
//            val datePickerKepulanganFragment = Kembali()
//            datePickerKepulanganFragment.listener = this
//            datePickerKepulanganFragment.show(
//                parentFragmentManager,
//                datePickerKepulanganFragment.tag
//            )
            roundTrip()
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

        getListView()
    }


    override fun totalPenumpang(total: String) {
        binding.etPenumpang.setText("$total Penumpang")
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

    private fun tripOneway() {
        searchViewModel.simpanTripOneway(true)
        searchViewModel.hapusTanggalKembali()
        searchViewModel.getTanggalKeberangkatan().observe(viewLifecycleOwner) {
            if (it != null) {
                binding.etKeberangkatan.text
            }
        }

        val today = MaterialDatePicker.todayInUtcMilliseconds()
        val materialDateBuilder = MaterialDatePicker.Builder.datePicker()
            .setTitleText("Pilih Tanggal")
            .setSelection(today)
            .setCalendarConstraints(
                CalendarConstraints.Builder()
                    .setValidator(DateValidatorPointForward.now())
                    .build()
            )
        val materialDatePicker = materialDateBuilder.build()

        if (materialDatePicker.dialog == null && !materialDatePicker.isVisible) {
            materialDatePicker.show(childFragmentManager, "MATERIAL_DATE_PICKER")
        }
        materialDatePicker.addOnPositiveButtonClickListener {

            val selecteddateInMillis = it as Long
            var selectedDate = Date(selecteddateInMillis)
            val simpleFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val formattedDate = simpleFormat.format(selectedDate)
            binding.etKeberangkatan.text = formattedDate
            searchViewModel.getValueTanggalKeberangkatan().observe(viewLifecycleOwner) {
                searchViewModel.simpanTanggalKeberangkatan(formattedDate)
            }
        }
    }

    private fun roundTrip() {
        searchViewModel.hapusOnewayTrip()
        val today = MaterialDatePicker.todayInUtcMilliseconds()
        val materialDateBuilder = MaterialDatePicker.Builder.datePicker()
            .setTitleText("Pilih Tanggal")
            .setSelection(today)
            .setCalendarConstraints(
                CalendarConstraints.Builder()
                    .setValidator(DateValidatorPointForward.now())
                    .build()
            )
        val materialDatePicker = materialDateBuilder.build()

        if (materialDatePicker.dialog == null && !materialDatePicker.isVisible) {
            materialDatePicker.show(childFragmentManager, "MATERIAL_DATE_PICKER")
        }

        materialDatePicker.addOnPositiveButtonClickListener {

            val selecteddateInMillis = it as Long
            var selectedDate = Date(selecteddateInMillis)

            if (selectedDate.before(selectedDate)) {
                // Jika tanggal yang dipilih sebelum hari ini, atur tanggal kembali ke hari ini
                selectedDate = selectedDate
            }
            val simpleFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val formattedDate = simpleFormat.format(selectedDate)
            binding.etKepulangan.text = formattedDate
            searchViewModel.getValueTanggalKembali().observe(viewLifecycleOwner) {
                searchViewModel.simpanTanggalKembali(formattedDate)
            }
        }
    }

    private fun getListView() {

        searchViewModel.getKotaKeberangkatan().observe(viewLifecycleOwner) {
            if (it != null) {
                val editableText = Editable.Factory.getInstance().newEditable(it)
                binding.etAsallokasi.text = editableText
                bund.putString("KotaKeberangkatan", it)
            }
        }
//        searchViewModel.getKodeKotaKeberangkatan().observe(viewLifecycleOwner){
//            if (it != null){
//                binding.txtCitycodeDeparture.text = it
//            }
//        }
        searchViewModel.getKotaDestinasi().observe(viewLifecycleOwner) {
            if (it != null) {
                val editableText = Editable.Factory.getInstance().newEditable(it)
                binding.etTujuanlokasi.text = editableText
                bund.putString("KotaDestinasi", it)
            }
        }
//        searchViewModel.getCityCodeDestination().observe(viewLifecycleOwner){
//            if (it != null){
//                binding.txtCitycodeDestination.text = it
//            }
//        }
        searchViewModel.getTanggalKeberangkatan().observe(viewLifecycleOwner) {
            if (it != null) {
//                val simpleFormat = "yyyy-MM-dd"
//                val itDepartureDate = SimpleDateFormat(simpleFormat, Locale.US)
                bund.putString("TanggalKeberangkatan", it.toString())
            }
        }

        searchViewModel.getKelasKursi().observe(viewLifecycleOwner) {
            if (it != null) {
                val editableText = Editable.Factory.getInstance().newEditable(it)
                binding.etKelaskursi.text = editableText
                bund.putString("KelasKursi", it)
            }
        }
        searchViewModel.getValueTripOneway().observe(viewLifecycleOwner) {
            if (it == true) {
                searchViewModel.hapusTanggalKembali()
                bund.putString("TanggalKembali", "")
            } else {
                searchViewModel.getTanggalKembali().observe(viewLifecycleOwner) {
                    if (it != null) {
                        bund.putString("TanggalKembali", it.toString())
                    }
                }
            }
        }

        binding.btnCaripenerbangan.setOnClickListener {
            findNavController().navigate(R.id.action_home3_to_resultSearch, bund)
        }

    }
}


