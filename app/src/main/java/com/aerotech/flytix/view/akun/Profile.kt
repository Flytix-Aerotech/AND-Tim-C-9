package com.aerotech.flytix.view.akun

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.aerotech.flytix.R
import com.aerotech.flytix.databinding.FragmentProfileBinding
import com.aerotech.flytix.model.DataUserProfilePutItem
import com.aerotech.flytix.viewmodel.ProfileViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Profile : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private lateinit var userVm: ProfileViewModel
    private lateinit var pref: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userVm = ViewModelProvider(this).get(ProfileViewModel::class.java)
        pref = requireContext().getSharedPreferences("LOGIN", Context.MODE_PRIVATE)
        getdataprofile()

//        binding.btnGantiProfile.setOnClickListener {
//            findNavController().navigate(R.id.action_profileFragment2_to_homeFragment2)
//        }

        binding.btnGantiProfile.setOnClickListener {
            updateUserProfile()
        }
    }

    fun updateUserProfile() {
//        val pass = pref.getString("password", "").toString()

        val token = pref.getString("token", "").toString()
        val inputnama = binding.etFullNameBaru.text.toString()
        val inputtlp = binding.etNomorTeleponBaru.text.toString()
        val inputemail = binding.etEmailBaru.text.toString()
        val inputusername = binding.etUsernameBaru.text.toString()
        val dataUser = DataUserProfilePutItem(inputemail, inputnama, inputtlp, inputusername)

        userVm.updateprofile(token, dataUser)
        navigationBundlingSf(dataUser)
        userVm.livedataupdateprofile.observe(viewLifecycleOwner) {
            if (it != null) {
                Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_profile_to_akun2)
            }
        }
    }

    fun getdataprofile() {
        val token = pref.getString("token", "").toString()
        userVm.getDataProfile(token)
        userVm.authLiveDataUserProfile.observe(viewLifecycleOwner) {
            Log.d("Profile", "email : ${it!!.user.email}")
            binding.etEmailBaru.setText(it.user.email)
            binding.etNomorTeleponBaru.setText(it.user.phoneNumber)
            binding.etFullNameBaru.setText(it.user.fullName)
            binding.etUsernameBaru.setText(it.user.username)
        }

    }

    private fun navigationBundlingSf(currentUser: DataUserProfilePutItem) {
        //shared pref to save log in history
        val sharedPref = pref.edit()
        sharedPref.putString("email", currentUser.email)
        sharedPref.putString("telephone", currentUser.phoneNumber)
        sharedPref.putString("fullname", currentUser.fullName)
        sharedPref.putString("username", currentUser.username)
        sharedPref.apply()
    }
}