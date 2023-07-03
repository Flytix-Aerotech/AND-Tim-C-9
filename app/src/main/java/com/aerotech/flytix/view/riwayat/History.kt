package com.aerotech.flytix.view.riwayat

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.aerotech.flytix.R
import com.aerotech.flytix.databinding.FragmentHistoryBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class History : Fragment() {
    private lateinit var binding: FragmentHistoryBinding
    lateinit var sharedPref: SharedPreferences
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        login()
    }

    private fun login() {
        binding.btnLogin.setOnClickListener {
            view?.post {
                findNavController().navigate(R.id.action_history_to_login2)
            }
        }
    }

    private fun isLogin() {
        sharedPref = requireContext().getSharedPreferences("LOGIN", Context.MODE_PRIVATE)
        if (sharedPref.getString("token", "")!!.isEmpty()) {
            binding.layoutloginHistori.visibility = View.GONE
            binding.layoutNoLoginHistori.visibility = View.VISIBLE
        } else {
            binding.layoutloginHistori.visibility = View.VISIBLE
            binding.layoutNoLoginHistori.visibility = View.GONE
        }
    }

    override fun onResume() {
        super.onResume()
        isLogin()
    }
}