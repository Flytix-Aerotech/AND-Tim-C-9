package com.aerotech.flytix.view.home

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.Editable
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.aerotech.flytix.R
import com.aerotech.flytix.databinding.FragmentHomeBinding
import com.aerotech.flytix.databinding.LayoutDialogPenumpangBinding
import com.aerotech.flytix.viewmodel.SearchViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.DateValidatorPointForward
import com.google.android.material.datepicker.MaterialDatePicker
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@AndroidEntryPoint
class Home : Fragment(),
    KelasKursi.kelasKursiListener, Darimana.getItemkotaListener,
    Tujuan.getItemkotaTujuanListener {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var searchViewModel: SearchViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment

        searchViewModel = ViewModelProvider(this)[SearchViewModel::class.java]
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
            setPassengers()
        }


        binding.etKeberangkatan.setOnClickListener {
            tripOneway()
        }


        binding.etKepulangan.setOnClickListener {
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

        binding.btnCaripenerbangan.setOnClickListener {
//            navtoResultSearch()
        }

    }

//    fun navtoResultSearch(){
//            findNavController().navigate(R.id.action_home3_to_resultSearch,)
//            Log.d("DATA_PASSENGER", searchViewModel.dataPassenger.value.toString())
//    }


    override fun pilihKelasKursi(kelas: String) {
        binding.etKelaskursi.setText(kelas)
    }

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
                bund.putString("TanggalKeberangkatan", it.toString())
            }
        }
        searchViewModel.getJumlahPenumpang().observe(viewLifecycleOwner) {
            if (it != null) {
                val editableText = Editable.Factory.getInstance().newEditable(it.toString())
                binding.etPenumpang.text = editableText
                bund.putString("JumlahPenumpang", it)

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
            findNavController().navigate(R.id.action_home3_to_resultSearch,bund)
        }
//        val numSeatPassenger = searchViewModel.dataPassenger.value
//        bund.putIntArray("DATA_LIST_NUM_SEAT", numSeatPassenger!!.toIntArray())
    }

    //SET COUNT PASSENGERS
    //mengatur jumlah penumpang berdasarkan kategori usia dari dewasa, anak-anak, hingga balita
    private fun setPassengers() {
        //bottom sheet
        val dialog = BottomSheetDialog(requireContext())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.layout_dialog_penumpang)
        val bindingDialog = LayoutDialogPenumpangBinding.inflate(layoutInflater)
        dialog.setContentView(bindingDialog.root)

        //deklarsi dari view model
        //set konfigurasi jumlah penumpang yang di dapatkan dari viewmodel
//        searchViewModel.getArrayJumlahpenumpang().observe(viewLifecycleOwner){
//            if (it!= null){
//                bindingDialog.tvJumlahpenumpangdewasa.text = it!![].toString()
//                bindingDialog.tvJumlahpenumpanganak.text = it.toString()
//                bindingDialog.tvJumlahpenumpangbayi.text = it.toString()
//                searchViewModel.simpanArrayJumlahPenumpang(it)
//                bund.putIntArray("ListJumlahPenumpang", it)
//                Log.d("DATA_PASSENGER", it.toString())
//            }
//        }
//        val numSeatPassenger = searchViewModel.dataPassenger.value
//        searchViewModel.simpanArrayJumlahPenumpang(numSeatPassenger!!)
//        searchViewModel.getArrayJumlahpenumpang().observe(viewLifecycleOwner){
//            if (it != null){
//                bindingDialog.tvJumlahpenumpangdewasa.text = it[0].toString()
//                bindingDialog.tvJumlahpenumpanganak.text = it[1].toString()
//                bindingDialog.tvJumlahpenumpangbayi.text = it[2].toString()
//                searchViewModel.simpanArrayJumlahPenumpang(it)
//            }
//        }
        if (searchViewModel.dataPassenger.value != null) {
            bindingDialog.tvJumlahpenumpangdewasa.text =
                searchViewModel.dataPassenger.value!![0].toString()
            bindingDialog.tvJumlahpenumpanganak.text =
                searchViewModel.dataPassenger.value!![1].toString()
            bindingDialog.tvJumlahpenumpangbayi.text =
                searchViewModel.dataPassenger.value!![2].toString()
        }

        bindingDialog.btnClose.setOnClickListener {
            dialog.dismiss()
        }

        setTotalPassengers(bindingDialog)


        bindingDialog.btnsimpanpenumpang.setOnClickListener {
            //apakah baby dihitung ???
            //simpan hasil total dan komposisi jumlah penumpang dengan livedata<List> di viewmodel
            val totalPassenger = countPassengers(
                bindingDialog.tvJumlahpenumpangdewasa,
                bindingDialog.tvJumlahpenumpanganak,
                bindingDialog.tvJumlahpenumpangbayi
            )

            if (totalPassenger >= 1) {

                //set data jumlah passenger ke dalam viewmodel
                searchViewModel.setDataPassenger(
                    0,
                    bindingDialog.tvJumlahpenumpangdewasa.text.toString().toInt()
                )
                searchViewModel.setDataPassenger(
                    1,
                    bindingDialog.tvJumlahpenumpanganak.text.toString().toInt()
                )
                searchViewModel.setDataPassenger(
                    2,
                    bindingDialog.tvJumlahpenumpangbayi.text.toString().toInt()
                )

                val total = "$totalPassenger Penumpang"
                searchViewModel.simpanJumlahPenumpang(total)
//                binding.tvPassengers.text = total
            }

            dialog.dismiss()
        }
        dialog.show()
        dialog.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        );
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT));
//        dialog.window?.attributes?.windowAnimations = R.style.DialogAnimation;
        dialog.window?.setGravity(Gravity.BOTTOM);
    }

    private fun setTotalPassengers(bindingDialog: LayoutDialogPenumpangBinding) {
        bindingDialog.imgTambahDewasa.setOnClickListener {
            val totalAdult = bindingDialog.tvJumlahpenumpangdewasa.text.toString().toInt() + 1
            bindingDialog.tvJumlahpenumpangdewasa.text = totalAdult.toString()
        }

        bindingDialog.imbTambahAnak.setOnClickListener {
            val totalChild = bindingDialog.tvJumlahpenumpanganak.text.toString().toInt() + 1
            bindingDialog.tvJumlahpenumpanganak.text = totalChild.toString()
        }
        bindingDialog.imbTambahBayi.setOnClickListener {
            val totalBaby = bindingDialog.tvJumlahpenumpangbayi.text.toString().toInt() + 1
            bindingDialog.tvJumlahpenumpangbayi.text = totalBaby.toString()
        }

        bindingDialog.imbKurangdewasa.setOnClickListener {
            var totalAdult = bindingDialog.tvJumlahpenumpangdewasa.text.toString().toInt()
            if (totalAdult >= 1) {
                totalAdult -= 1
            }
            bindingDialog.tvJumlahpenumpangdewasa.text = totalAdult.toString()
        }
        bindingDialog.imbKurangAnak.setOnClickListener {
            var totalChild = bindingDialog.tvJumlahpenumpanganak.text.toString().toInt()
            if (totalChild >= 1) {
                totalChild -= 1
            }
            bindingDialog.tvJumlahpenumpanganak.text = totalChild.toString()
        }
        bindingDialog.imbKurangBayi.setOnClickListener {
            var totalBaby = bindingDialog.tvJumlahpenumpangbayi.text.toString().toInt()
            if (totalBaby >= 1) {
                totalBaby -= 1
            }
            bindingDialog.tvJumlahpenumpangbayi.text = totalBaby.toString()
        }
    }

    private fun countPassengers(vararg passangers: TextView): Int {
        var count = 0
        for (item in passangers) {
            count += item.text.toString().toInt()
        }
        return count
    }

    override fun onResume() {
        super.onResume()
        getListView()
    }
}


