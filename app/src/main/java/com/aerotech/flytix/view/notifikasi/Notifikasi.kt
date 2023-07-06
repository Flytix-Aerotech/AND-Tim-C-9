package com.aerotech.flytix.view.notifikasi

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.aerotech.flytix.R
import com.aerotech.flytix.databinding.FragmentNotifikasiBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Notifikasi : Fragment() {
    private lateinit var binding: FragmentNotifikasiBinding
    lateinit var sharedPref: SharedPreferences
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentNotifikasiBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        login()
    }

    private fun login() {
//        binding.btnLogin.setOnClickListener {
//            view?.post {
//                findNavController().navigate(R.id.action_history_to_login2)
//            }
//        }
    }

    private fun isLogin() {
        sharedPref = requireContext().getSharedPreferences("LOGIN", Context.MODE_PRIVATE)
        if (sharedPref.getString("token", "").toString().isNotEmpty()) {
            if (findNavController().currentDestination!!.id == R.id.notifikasi2) {
                binding.layoutloginHistori.visibility = View.VISIBLE
                binding.layoutNoLoginHistori.visibility = View.GONE
            }
        } else {
            binding.layoutloginHistori.visibility = View.GONE
            binding.layoutNoLoginHistori.visibility = View.VISIBLE
            findNavController().navigate(R.id.checkLoginUser)
        }
    }

    override fun onResume() {
        super.onResume()
        isLogin()
    }
}