package com.aerotech.flytix.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.aerotech.flytix.databinding.FragmentPenumpangBinding

class Penumpang : Fragment() {

    private lateinit var binding: FragmentPenumpangBinding

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
//        binding.btnCaripenerbangan.setOnClickListener{
//            val fragmentB = Login()
//            fragmentB.show(parentFragmentManager,fragmentB.tag)
//        }
    }

}