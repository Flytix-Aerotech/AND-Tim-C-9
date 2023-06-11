package com.aerotech.flytix.view.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.aerotech.flytix.databinding.FragmentHomeBinding


class Home : Fragment(), Keberangkatan.DatePickerListener, Kembali.DatePickerListener,
    Penumpang.totalPenumpangListener {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.etPenumpang.setOnClickListener {
            val fragmentBottomPenumpang = Penumpang()
            fragmentBottomPenumpang.listener = this
            fragmentBottomPenumpang.show(parentFragmentManager, fragmentBottomPenumpang.tag)
        }

        binding.etKeberangkatan.setOnClickListener {
            val datePickerKeberangkatanFragment = Keberangkatan()
            datePickerKeberangkatanFragment.listener = this
            datePickerKeberangkatanFragment.show(parentFragmentManager, "datePicker")
        }

        binding.etKepulangan.setOnClickListener {
            val datePickerKepulanganFragment = Kembali()
            datePickerKepulanganFragment.listener = this
            datePickerKepulanganFragment.show(parentFragmentManager, "datePicker")
        }

        binding.switchPp.setOnCheckedChangeListener {switchView, ischecked ->
            if (ischecked){
                binding.tvKepulangan.visibility = View.VISIBLE
                binding.ivKepulangan.visibility = View.VISIBLE
                binding.etKepulangan.visibility = View.VISIBLE

            }else{
                binding.tvKepulangan.visibility = View.GONE
                binding.ivKepulangan.visibility = View.GONE
                binding.etKepulangan.visibility = View.GONE
            }
        }
    }


    override fun onDateSelected(date: String) {
        // Set the selected date in the EditText
        binding.etKeberangkatan.setText(date)
    }

    override fun onDateSelectedKepulangan(date: String) {
        // Set the selected date in the EditText
        binding.etKepulangan.setText(date)
    }

    override fun totalPenumpang(total: String) {
        binding.etPenumpang.setText("$total Penumpang")
    }

}
