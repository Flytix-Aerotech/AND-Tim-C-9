package com.aerotech.flytix.view.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.aerotech.flytix.databinding.FragmentKeberangkatanBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import kotlin.properties.Delegates
@AndroidEntryPoint
class Keberangkatan : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentKeberangkatanBinding
    internal var listener: DatePickerListener? = null
    var fieldNumber by Delegates.notNull<Int>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentKeberangkatanBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SuspiciousIndentation")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set initial date to current date
        val calendar = Calendar.getInstance()
        binding.dpKeberangkatan.minDate = calendar.timeInMillis
            binding.dpKeberangkatan.init(
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            ) { _, year, monthOfYear, dayOfMonth ->
                // Handle date selection here
                // Handle date selection here
                val selectedDate = formatDate(year, monthOfYear, dayOfMonth)
                listener?.onDateSelected(selectedDate)
                dismiss() // Close the bottom sheet dialog
            }
    }

    private fun formatDate(year: Int, month: Int, day: Int): String {
        val calendar = Calendar.getInstance()
        calendar.set(year, month, day)
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        return dateFormat.format(calendar.time)
    }

    interface DatePickerListener {
        fun onDateSelected(date: String)
    }
}