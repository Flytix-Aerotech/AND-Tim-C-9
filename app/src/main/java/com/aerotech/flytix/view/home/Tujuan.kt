package com.aerotech.flytix.view.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.aerotech.flytix.databinding.FragmentTujuanBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Tujuan : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentTujuanBinding
    internal var listener: TujuanListener? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentTujuanBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.icClosebottomsheettujuan.setOnClickListener {
            dismiss()
        }
    }


    interface TujuanListener {
        fun onSelectedTujuan(tujuan: String)
    }
}