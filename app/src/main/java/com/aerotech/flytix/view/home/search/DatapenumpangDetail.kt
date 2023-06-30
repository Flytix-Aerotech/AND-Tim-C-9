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
import com.aerotech.flytix.viewmodel.PenumpangViewModel
import com.dwiki.tiketku.model.penumpang.PenumpangData
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import java.util.Calendar


@AndroidEntryPoint
class DatapenumpangDetail : BottomSheetDialogFragment() {
    private lateinit var binding:FragmentDatapenumpangDetailBinding
    private val penumpangViewModel:PenumpangViewModel by activityViewModels()
    private var titleAd:String? = null


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
        //val hintTitle = resources.getStringArray(R.array.title)
        binding.tvPassengers.text = role
        binding.tvJudulPenumpang.text = "Biodata Penumpang $penumpang"

        val dataList = penumpangViewModel.getDataList()
        //formTitle(hintTitle)

        if (dataList.isNotEmpty()){
            if (indexPenumpang in dataList.indices){
                Log.d("Data","${dataList.indices}")
                Log.d("Data","${dataList[0]}")
                val itemPenumpang = dataList[indexPenumpang!!]
                binding.tvPassengers.setText(itemPenumpang.role)
                binding.etNamaLengkapPenumpang.setText(itemPenumpang.name)
                binding.etNameClan.setText(itemPenumpang.familyName)
                binding.etDateOfBirthPassenger.setText(itemPenumpang.dateofbirth)
                binding.etCitizenship.setText(itemPenumpang.citizenship)
                binding.etIDorPassport.setText(itemPenumpang.ktppaspor)
                editData(dataList, indexPenumpang)
            } else {
                sumbitData()
            }
        } else {
            sumbitData()
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
                requireContext(),R.style.DateDialogTheme,
                { _, year, month, dayOfMonth ->
                    val tanggalLahir = "$year-0${month+1}-$dayOfMonth"
                    binding.etDateOfBirthPassenger.setText(tanggalLahir)
                },
                year, month, day,
            )
            datePickerDialog.show()
            datePickerDialog.getButton(DatePickerDialog.BUTTON_NEGATIVE).setTextColor(resources.getColor(R.color.black))
            datePickerDialog.getButton(DatePickerDialog.BUTTON_POSITIVE).setTextColor(resources.getColor(R.color.black))
        }
    }

    private fun editData(
        dataList: List<PenumpangData>,
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

            dataList[indexPenumpang!!].role = role
            dataList[indexPenumpang!!].name = name
            dataList[indexPenumpang].familyName = namaKeluarga
            dataList[indexPenumpang].dateofbirth = tanggalLahir
            dataList[indexPenumpang].citizenship = kewarganegaraan
            dataList[indexPenumpang].ktppaspor = ktp
            dismiss()
        }
    }

    private fun sumbitData() {
        binding.btnSimpan.setOnClickListener {
            Log.d("Detail Biodata", "On Clicckkk Submit Data")
            val role = binding.tvPassengers.text.toString()
            val name = binding.etNamaLengkapPenumpang.text.toString()
            val namaKeluarga = binding.etNameClan.text.toString()
            val tanggalLahir = binding.etDateOfBirthPassenger.text.toString()
            val kewarganegaraan = binding.etCitizenship.text.toString()
            val ktp = binding.etIDorPassport.text.toString()

            val dataPenumpang =
                PenumpangData(ktp, tanggalLahir, namaKeluarga, kewarganegaraan, name, role)
            penumpangViewModel.addData(dataPenumpang)
            dismiss()
        }
    }

//    private fun formTitle(hintTitle: Array<String>) {
//        binding.edtTitle.apply {
//            val adapterTitle = ArrayAdapter(requireContext(), R.layout.dropdown_item, hintTitle)
//            setAdapter(adapterTitle)
//            hint = "Title"
//            onItemClickListener = AdapterView.OnItemClickListener{ _, _, position, _ ->
//                titleAd = adapterTitle.getItem(position).toString()
//            }
//        }
//    }

}