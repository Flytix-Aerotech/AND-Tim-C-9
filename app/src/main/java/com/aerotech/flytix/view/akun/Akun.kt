package com.aerotech.flytix.view.akun

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.aerotech.flytix.R
import com.aerotech.flytix.databinding.FragmentAkunBinding
import com.aerotech.flytix.viewmodel.ProfileViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Akun : Fragment() {

    private lateinit var binding: FragmentAkunBinding
    private lateinit var userVm: ProfileViewModel
    private lateinit var pref: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAkunBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.etUbahprofile.setOnClickListener {
            findNavController().navigate(R.id.action_akun2_to_profile)
        }
        binding.etKeluar.setOnClickListener {
            logout()
        }
    }

    private fun logout() {
        pref =
            requireActivity().getSharedPreferences("LOGIN", Context.MODE_PRIVATE)
        //shared pref to save log in history
        val sharedPref = pref.edit()
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Logout")
            .setMessage("Anda Yakin?")
            .setCancelable(false)
            .setNegativeButton("Cancel") { dialog, which ->
                // Respond to negative button press
                dialog.cancel()
            }
            .setPositiveButton("Logout") { dialog, which ->
                // Respond to positive button press
                sharedPref.clear()
                sharedPref.apply()
                activity?.onBackPressed()
            }
            .show()
    }

    private fun isLogin() {
        pref = requireContext().getSharedPreferences("LOGIN", Context.MODE_PRIVATE)
        if (pref.getString("token", "").toString().isNotEmpty()) {
            if (findNavController().currentDestination!!.id == R.id.akun2) {
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