package com.aerotech.flytix.view.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.aerotech.flytix.R
import com.aerotech.flytix.databinding.FragmentPaymentBinding

class Payment : Fragment() {
    lateinit var binding: FragmentPaymentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPaymentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val items = listOf("GOPAY", "OVO", "MANDIRI", "BRI", "BCA",)
        val adapter = ArrayAdapter(requireContext(), R.layout.list_item, items)
        binding.autoText.setAdapter(adapter)
    }
}