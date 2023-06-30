package com.aerotech.flytix.view.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.aerotech.flytix.databinding.FragmentPenumpangBinding
import com.aerotech.flytix.viewmodel.SearchViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Penumpang : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentPenumpangBinding
    private lateinit var searchVM: SearchViewModel

    private var totalDewasa = 0
    private var totalAnak = 0
    private var totalBayi = 0
    var total = 0


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        searchVM = ViewModelProvider(this).get(SearchViewModel::class.java)
        binding = FragmentPenumpangBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        hitungDewasa()
        hitungAnak()
        hitungBayi()
        setClose()


        binding.btnSimpanpenumpang.setOnClickListener {
            dismiss()
        }


        Log.d("Sheet set Penumpang", "onViewCreated")

//        searchVM.getJumlahPenumpangDewasa().observe(viewLifecycleOwner) {
//            if (it != null) {
//                val jumlahDewasa = it
//                setDewasa("it")
//                binding.tvJumlahdewasa.text = jumlahDewasa.toString()
//
//            }
//        }
//        searchVM.getJumlahPenumpangAnak().observe(viewLifecycleOwner) {
//            if (it != null) {
//                val jumlahAnak = it
//                binding.tvJumlahanak.text = jumlahAnak.toString()
//                setAnak(it)
//            }
//        }
//        searchVM.getJumlahPenumpangDewasa().observe(viewLifecycleOwner) {
//            if (it != null) {
//                val jumlahBayi = it
//                binding.tvJumlahbayi.text = jumlahBayi.toString()
//                setBayi(it)
//            }
//        }
//
//        binding.btnSimpanpenumpang.setOnClickListener {
//            val tvDewasa = binding.tvJumlahdewasa.text.toString()
//            val tvBayi = binding.tvJumlahbayi.text.toString()
//            val tvAnak = binding.tvJumlahanak.text.toString()
//            searchVM.simpanJumlahPenumpang(tvDewasa, tvAnak, tvBayi)
//            val total = tvDewasa.toInt() + tvBayi.toInt() + tvAnak.toInt()
//            searchVM.simpanJumlahTotalPenumpang(total.toString())
//            dismiss()
//        }
    }

    fun saveData(){
        val tvDewasa = binding.tvJumlahdewasa.text.toString()
        val tvBayi = binding.tvJumlahbayi.text.toString()
        val tvAnak = binding.tvJumlahanak.text.toString()
        searchVM.simpanJumlahPenumpang(tvDewasa, tvAnak, tvBayi)
        var total = tvDewasa.toInt() + tvBayi.toInt() + tvAnak.toInt()
        searchVM.simpanJumlahTotalPenumpang(total.toString())
    }
    private fun setClose() {
        binding.icClosebottomsheet.setOnClickListener {
            dismiss()
        }
    }

    private fun setBayi(tiketBayi: String): Int {
        binding.tvJumlahbayi.text = tiketBayi.toString()
        var tiketBayi1 = tiketBayi.toInt()

        binding.ivAddboxbayi.setOnClickListener {
            tiketBayi1 += 1
            binding.tvJumlahbayi.text = tiketBayi1.toString()
        }

        binding.ivMinboxbayi.setOnClickListener {
            if (tiketBayi1 > 0) {
                tiketBayi1 -= 1
                binding.tvJumlahbayi.text = tiketBayi1.toString()
            }
        }
        return tiketBayi1
    }

    private fun setAnak(tiketAnak: String): Int {
        binding.tvJumlahanak.text = tiketAnak.toString()
        var tiketAnak1 = tiketAnak.toInt()
        binding.ivAddboxanak.setOnClickListener {
            tiketAnak1 += 1
            binding.tvJumlahanak.text = tiketAnak1.toString()
        }

        binding.ivMinboxanak.setOnClickListener {
            if (tiketAnak1 > 0) {
                tiketAnak1 -= 1
                binding.tvJumlahanak.text = tiketAnak1.toString()
            }
        }
        return tiketAnak1
    }


    private fun setDewasa(tiketDewasa: String): Int {
        binding.tvJumlahdewasa.text = tiketDewasa
        var tiketDewasa1 = tiketDewasa.toInt()
        binding.ivAddboxdewasa.setOnClickListener {
            tiketDewasa1 += 1
            binding.tvJumlahdewasa.text = tiketDewasa1.toString()

        }

        binding.ivMinboxdewasa.setOnClickListener {
            if (tiketDewasa1 > 0) {
                tiketDewasa1 -= 1
                binding.tvJumlahdewasa.text = tiketDewasa1.toString()
            }
        }
        return tiketDewasa1
    }

//    override fun onResume() {
//        super.onResume()
////        searchVM = ViewModelProvider(this).get(SearchViewModel::class.java)
////        Log.d("Sheet set Penumpang", "onViewCreated")
////
////        searchVM.getJumlahPenumpangDewasa().observe(viewLifecycleOwner) {
////            if (it != null) {
////                binding.tvJumlahdewasa.text = it
////            }
////        }
////        searchVM.getJumlahPenumpangAnak().observe(viewLifecycleOwner) {
////            if (it != null) {
////                binding.tvJumlahanak.text = it
////            }
////        }
////        searchVM.getJumlahPenumpangBayi().observe(viewLifecycleOwner) {
////            if (it != null) {
////                binding.tvJumlahbayi.text = it
////            }
////        }
////
////        binding.btnSimpanpenumpang.setOnClickListener {
////            val tvDewasa = binding.tvJumlahdewasa.text.toString()
////            val tvBayi = binding.tvJumlahbayi.text.toString()
////            val tvAnak = binding.tvJumlahanak.text.toString()
////            searchVM.simpanJumlahPenumpang(tvDewasa, tvAnak, tvBayi)
////            val total = tvDewasa.toInt() + tvBayi.toInt() + tvAnak.toInt()
////            searchVM.simpanJumlahTotalPenumpang(total.toString())
////            dismiss()
//        }
//    }


    private fun updateTotal() {
        binding.tvJumlahdewasa.text = totalDewasa.toString()
        binding.tvJumlahanak.text = totalAnak.toString()
        binding.tvJumlahbayi.text = totalBayi.toString()
        saveData()
    }

    fun hitungDewasa(): Int {
        searchVM.getJumlahPenumpangDewasa().observe(viewLifecycleOwner) {
            if (it != null) {
                totalDewasa = it.toInt()
                binding.ivMinboxdewasa.setOnClickListener {
                    if (totalDewasa > 0) {
                        totalDewasa--
                        updateTotal()
                    }
                }
                binding.ivAddboxdewasa.setOnClickListener {
                    totalDewasa++
                    updateTotal()
                }
            }
        }
        return totalDewasa
    }

    fun hitungAnak(): Int {
        searchVM.getJumlahPenumpangAnak().observe(viewLifecycleOwner){
            if (it != null){
                totalAnak = it.toInt()
                binding.ivMinboxanak.setOnClickListener {
                    if (totalAnak > 0) {
                        totalAnak--
                        updateTotal()
                    }
                }
                binding.ivAddboxanak.setOnClickListener {
                    totalAnak++
                    updateTotal()
                }
            }
        }
        return totalAnak
    }


    fun hitungBayi(): Int {
        searchVM.getJumlahPenumpangBayi().observe(viewLifecycleOwner){
            if (it!=null){
                totalBayi = it.toInt()
                binding.ivMinboxbayi.setOnClickListener {
                    if (totalBayi > 0) {
                        totalBayi--
                        updateTotal()
                    }
                }
                binding.ivAddboxbayi.setOnClickListener {
                    totalBayi++
                    updateTotal()
                }
            }
        }
        return totalBayi
    }

    override fun onResume() {
        super.onResume()
        updateTotal()
    }
}