package com.aerotech.flytix.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
<<<<<<<< HEAD:app/src/main/java/com/aerotech/flytix/view/TiketFragment.kt
import androidx.fragment.app.Fragment
import com.aerotech.flytix.R
import dagger.hilt.android.AndroidEntryPoint

========
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.aerotech.flytix.R
import com.aerotech.flytix.databinding.FragmentResultSearchBinding


class ResultSearch : Fragment() {
    lateinit var binding : FragmentResultSearchBinding
>>>>>>>> 145353e (update Result):app/src/main/java/com/aerotech/flytix/view/ResultSearch.kt

@AndroidEntryPoint
class TiketFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
<<<<<<<< HEAD:app/src/main/java/com/aerotech/flytix/view/TiketFragment.kt
        return inflater.inflate(R.layout.fragment_tiket, container, false)
    }

========
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


>>>>>>>> 145353e (update Result):app/src/main/java/com/aerotech/flytix/view/ResultSearch.kt
}