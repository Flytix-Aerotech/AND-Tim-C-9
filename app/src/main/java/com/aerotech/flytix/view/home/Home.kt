package com.aerotech.flytix.view.home

import android.app.DatePickerDialog
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.aerotech.flytix.R
import com.aerotech.flytix.databinding.FragmentHomeBinding
import com.aerotech.flytix.view.adapter.DestinasiFavoriteAdapter
import com.aerotech.flytix.viewmodel.FlightViewModel
import com.aerotech.flytix.viewmodel.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@AndroidEntryPoint
class Home : Fragment(),
    KelasKursi.kelasKursiListener, Darimana.getItemkotaListener,
    Tujuan.getItemkotaTujuanListener {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var searchViewModel: SearchViewModel
    private lateinit var flightViewModel: FlightViewModel
    lateinit var bund: Bundle
    lateinit var pref: SharedPreferences
    lateinit var adapterfavDestinasi: DestinasiFavoriteAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        searchViewModel = ViewModelProvider(this)[SearchViewModel::class.java]
        flightViewModel = ViewModelProvider(this)[FlightViewModel::class.java]
        bund = Bundle()
        pref = requireContext().getSharedPreferences("LOGIN", Context.MODE_PRIVATE)
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
            val setpenumpang = Penumpang()
            setpenumpang.show(parentFragmentManager, setpenumpang.tag)
        }

        binding.etKeberangkatan.setOnClickListener {
            pilihTanggalkeberangkatan()
        }
        searchViewModel.getTanggalKeberangkatan().observe(viewLifecycleOwner) {
            if (it != null) {
                binding.etKeberangkatan.text = it
            }
        }

        binding.etKepulangan.setOnClickListener {
            pilihTanggalKepulangan()
        }
        searchViewModel.getTanggalKembali().observe(viewLifecycleOwner) {
            if (it != null) {
                binding.etKepulangan.text = it
            }
        }


        binding.switchPp.setOnCheckedChangeListener { _, ischecked ->
            if (ischecked) {
                searchViewModel.simpanTripOneway(true)
                binding.tvKepulangan.visibility = View.VISIBLE
                binding.ivKepulangan.visibility = View.VISIBLE
                binding.etKepulangan.visibility = View.VISIBLE
                Log.d("Beranda Fragment", "switch true")
            } else {
                searchViewModel.simpanTripOneway(false)
                binding.tvKepulangan.visibility = View.GONE
                binding.ivKepulangan.visibility = View.GONE
                binding.etKepulangan.visibility = View.GONE
                Log.d("Beranda Fragment", "switch false")
            }
        }


        binding.etKelaskursi.setOnClickListener {
            val pilihKelasKursi = KelasKursi()
            pilihKelasKursi.listener = this
            pilihKelasKursi.show(parentFragmentManager, pilihKelasKursi.tag)
        }

        getListViewDS()
    }

    private fun getListViewDS() {
        val bund = Bundle()
        searchViewModel.getKotaKeberangkatan().observe(viewLifecycleOwner) {
            if (it != null) {
                val editableText = Editable.Factory.getInstance().newEditable(it)
                binding.etAsallokasi.text = editableText
                bund.putString("KotaKeberangkatan", it)
            }
        }
        searchViewModel.getKotaDestinasi().observe(viewLifecycleOwner) {
            if (it != null) {
                val editableText = Editable.Factory.getInstance().newEditable(it)
                binding.etTujuanlokasi.text = editableText
                bund.putString("KotaDestinasi", it)
            }
        }
        searchViewModel.getTanggalKeberangkatan().observe(viewLifecycleOwner) {
            if (it != null) {
                bund.putString("TanggalKeberangkatan", it)
            }
        }

        searchViewModel.getJumlahTotalPenumpang().observe(viewLifecycleOwner) {
            if (it != null) {
                var editableText = Editable.Factory.getInstance().newEditable(it + " Penumpang")
                binding.etPenumpang.text = editableText
                val sharedPref = pref.edit()
                sharedPref.putString("jumlahPenumpang", it)
                sharedPref.apply()
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
            if (it == false) {
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
        searchViewModel.getValueTripOneway().observe(viewLifecycleOwner) {
            if (it != null) {
                binding.switchPp.isChecked = it
                var check = it
                searchViewModel.simpanTripOneway(check)
                Log.d("home fragment", "$check")
                binding.btnCaripenerbangan.setOnClickListener {
                    if (check == false) {
                        searchViewModel.hapusTanggalKembali()
                        findNavController().navigate(R.id.action_home3_to_pencarianTicketOw, bund)
                    } else {
                        findNavController().navigate(
                            R.id.action_home3_to_pencarianTicketRtDep,
                            bund
                        )
                    }
                }
            }
        }
    }

    private fun pilihTanggalkeberangkatan() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            requireContext(), R.style.DateDialogTheme,
            { _, year, month, dayOfMonth ->
                val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                val tanggalPerginya = dateFormat.format(Calendar.getInstance().apply {
                    set(Calendar.YEAR, year)
                    set(Calendar.MONTH, month)
                    set(Calendar.DAY_OF_MONTH, dayOfMonth)
                }.time)
//                val bulan =nameMonth[month]
                binding.etKeberangkatan.setText(tanggalPerginya)
                searchViewModel.simpanTanggalKeberangkatan(tanggalPerginya)
            },
            year, month, day,
        )
        datePickerDialog.show()
        datePickerDialog.getButton(DatePickerDialog.BUTTON_NEGATIVE)
            .setTextColor(resources.getColor(R.color.black))
        datePickerDialog.getButton(DatePickerDialog.BUTTON_POSITIVE)
            .setTextColor(resources.getColor(R.color.black))
    }

    fun pilihTanggalKepulangan() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            requireContext(), R.style.DateDialogTheme,
            { _, year, month, dayOfMonth ->
                val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                val tanggalPerginya = dateFormat.format(Calendar.getInstance().apply {
                    set(Calendar.YEAR, year)
                    set(Calendar.MONTH, month)
                    set(Calendar.DAY_OF_MONTH, dayOfMonth)
                }.time)
//                val bulan =nameMonth[month]
                binding.etKepulangan.setText(tanggalPerginya)
                searchViewModel.simpanTanggalKembali(tanggalPerginya)
            },
            year, month, day,
        )
        datePickerDialog.show()
        datePickerDialog.getButton(DatePickerDialog.BUTTON_NEGATIVE)
            .setTextColor(resources.getColor(R.color.black))
        datePickerDialog.getButton(DatePickerDialog.BUTTON_POSITIVE)
            .setTextColor(resources.getColor(R.color.black))
    }

    override fun pilihKelasKursi(kelas: String) {
        binding.etKelaskursi.setText(kelas)
    }

    override fun getItemKota(kota: String) {
        binding.etAsallokasi.setText(kota)
    }

    override fun getItemKotaTujuan(kota: String) {
        binding.etTujuanlokasi.setText(kota)
    }

    override fun onResume() {
        super.onResume()
        getListViewDS()
        flightViewModel.getFavFlight()
        flightViewModel.favFlight.observe(viewLifecycleOwner){ data ->
            binding.rvHome.apply {
                layoutManager = GridLayoutManager(requireContext(),3)
                adapterfavDestinasi = DestinasiFavoriteAdapter(data.data ?: emptyList())
                adapter = adapterfavDestinasi
            }
        }
    }
}


