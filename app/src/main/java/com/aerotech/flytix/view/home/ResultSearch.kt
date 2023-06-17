package com.aerotech.flytix.view.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.aerotech.flytix.databinding.FragmentResultSearchBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ResultSearch : Fragment() {
    lateinit var binding: FragmentResultSearchBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentResultSearchBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        adapter = HariAdapter(ArrayList())
//        binding.rvHari.layoutManager = LinearLayoutManager(context,  LinearLayoutManager.HORIZONTAL, false)
//        binding.rvHari.adapter = adapter
//        setVM = ViewModelProvider(this).get(SetVM::class.java)
//        setVM.getData()
//        setVM.getDataHari.observe(viewLifecycleOwner, Observer {
//            adapter.setItemDataFilm(it as ArrayList<DataHari>)
//        })
//    }


}