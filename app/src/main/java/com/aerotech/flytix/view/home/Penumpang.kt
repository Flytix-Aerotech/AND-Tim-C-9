package com.aerotech.flytix.view.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.aerotech.flytix.databinding.FragmentPenumpangBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Penumpang : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentPenumpangBinding
    private var totalDewasa = 0
    private var totalAnak = 0
    private var totalBayi = 0
    var total = 0
    internal var listener: totalPenumpangListener? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentPenumpangBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.icClosebottomsheet.setOnClickListener {
            dismiss()
        }
        hitungDewasa()
        hitungAnak()
        hitungBayi()
//        updateTotalPenumpang()
    }

    private fun updateTotal() {
        binding.tvJumlahdewasa.text = totalDewasa.toString()
        binding.tvJumlahanak.text = totalAnak.toString()
        binding.tvJumlahbayi.text = totalBayi.toString()
        var totalPenumpang = totalDewasa + totalAnak + totalBayi
        binding.btnSimpanpenumpang.setOnClickListener {
            total = totalPenumpang
            listener?.totalPenumpang(total.toString())
            dismiss()
        }
    }

    fun hitungDewasa(): Int {
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
        return totalDewasa
    }

    fun hitungAnak(): Int {
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
        return totalAnak
    }


    fun hitungBayi(): Int {
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
        return totalBayi
    }

    interface totalPenumpangListener {
        fun totalPenumpang(total: String)
    }
}