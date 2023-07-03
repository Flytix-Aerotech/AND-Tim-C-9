package com.aerotech.flytix.view.home.search

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.aerotech.flytix.R
import com.aerotech.flytix.databinding.FragmentDatapenumpangDetailBinding
import com.aerotech.flytix.model.books.Passenger
import com.aerotech.flytix.viewmodel.BiodataViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


@AndroidEntryPoint
class DatapenumpangDetail : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentDatapenumpangDetailBinding
    private val biodataViewModel: BiodataViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDatapenumpangDetailBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.icClosebottomsheetDataPenumpang.setOnClickListener {
            dismiss()
        }
        val penumpang = arguments?.getString("penumpang")
        val role = arguments?.getString("role")
        val indexPenumpang = arguments?.getInt("index")
        binding.tvPassengers.text = role
        binding.tvJudulPenumpang.text = "Biodata Penumpang $penumpang"
        val dataList = biodataViewModel.getDataList()

        if (dataList.isNotEmpty()) {
            if (indexPenumpang in dataList.indices) {
                Log.d("Data", "${dataList.indices}")
                Log.d("Data", "${dataList[0]}")
                val itemPenumpang = dataList[indexPenumpang!!]
                binding.tvPassengers.setText(itemPenumpang.passengerRole)
                binding.etNamaLengkapPenumpang.setText(itemPenumpang.fullName)
                binding.etNameClan.setText(itemPenumpang.clanName)
                binding.etDateOfBirthPassenger.setText(itemPenumpang.birthDate)
                binding.etCitizenship.setText(itemPenumpang.nationality)
                binding.etIDorPassport.setText(itemPenumpang.nikNumber)
                editData(dataList, indexPenumpang)
            } else {
                submitData()
            }
        } else {
            submitData()
        }

        binding.optionClan.setOnCheckedChangeListener { p0, isChecked ->

            if (isChecked) {
                binding.layoutNameClan.visibility = View.VISIBLE

            } else {
                binding.layoutNameClan.visibility = View.GONE
            }
        }

        binding.etDateOfBirthPassenger.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(
                requireContext(), R.style.DateDialogTheme,
                { _, year, month, dayOfMonth ->
                    val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                    val tanggalLahir = dateFormat.format(Calendar.getInstance().apply {
                        set(Calendar.YEAR, year)
                        set(Calendar.MONTH, month)
                        set(Calendar.DAY_OF_MONTH, dayOfMonth)
                    }.time)
                    binding.etDateOfBirthPassenger.setText(tanggalLahir)
                },
                year, month, day,
            )
            datePickerDialog.show()
            datePickerDialog.getButton(DatePickerDialog.BUTTON_NEGATIVE)
                .setTextColor(resources.getColor(R.color.black))
            datePickerDialog.getButton(DatePickerDialog.BUTTON_POSITIVE)
                .setTextColor(resources.getColor(R.color.black))
        }
    }

    private fun editData(
        dataList: List<Passenger>,
        indexPenumpang: Int?
    ) {
        binding.btnSimpan.setOnClickListener {
            Log.d("Detail Biodat", "On Clicckkk")
            val role = binding.tvPassengers.text.toString()
            val name = binding.etNamaLengkapPenumpang.text.toString()
            val namaKeluarga = binding.etNameClan.text.toString()
            val tanggalLahir = binding.etDateOfBirthPassenger.text.toString()
            val kewarganegaraan = binding.etCitizenship.text.toString()
            val ktp = binding.etIDorPassport.text.toString()

            Log.d("detail biodata", "$name")

            dataList[indexPenumpang!!].passengerRole = role
            dataList[indexPenumpang!!].fullName = name
            dataList[indexPenumpang].clanName = namaKeluarga
            dataList[indexPenumpang].birthDate = tanggalLahir
            dataList[indexPenumpang].nationality = kewarganegaraan
            dataList[indexPenumpang].nikNumber = ktp
            dismiss()
        }
    }

    private fun submitData() {
        binding.btnSimpan.setOnClickListener {
            Log.d("Detail Biodata", "On Clicckkk Submit Data")
            val role = binding.tvPassengers.text.toString()
            val name = binding.etNamaLengkapPenumpang.text.toString()
            val namaKeluarga = binding.etNameClan.text.toString()
            val tanggalLahir = binding.etDateOfBirthPassenger.text.toString()
            val kewarganegaraan = binding.etCitizenship.text.toString()
            val ktp = binding.etIDorPassport.text.toString()
            val dataPenumpang =
                Passenger(tanggalLahir, 0, namaKeluarga, name, kewarganegaraan, ktp, role)
            biodataViewModel.addData(dataPenumpang)
            dismiss()
            Log.d("Data Penumpang", dataPenumpang.toString())
        }
    }
}