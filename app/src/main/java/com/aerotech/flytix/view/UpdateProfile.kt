package com.aerotech.flytix.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.aerotech.flytix.databinding.FragmentUpdateProfileBinding
import com.aerotech.flytix.model.User
import com.aerotech.flytix.model.updateprofile.PutDataUpdateProfile
import com.aerotech.flytix.viewmodel.ProfileViewModel

class UpdateProfile : Fragment() {
    private lateinit var binding: FragmentUpdateProfileBinding
    private lateinit var viewModel: ProfileViewModel
    private lateinit var user: User
    private lateinit var token : String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUpdateProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(ProfileViewModel::class.java)
        //user = User(/* pass the necessary user data here */)
        token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6NCwicm9sZSI6InVzZXIiLCJwaG90byI6Imh0dHBzOi8vaWsuaW1hZ2VraXQuaW8vdGlucGV0L0lNR18xNjg3MTc3MDQyMjEwX0JMMTJxYmlpdS5wbmciLCJpYXQiOjE2ODcxODk4NDl9.yGPc26rXvWJCgaARCwejbT8725lrTaCF8cT7Rn2WOms"
        getData()
        binding.btnGantiProfile.setOnClickListener {
            updateProfileUser()
        }
    }

    private fun getData() {
        viewModel.getDataProfile(token)
        viewModel.authLiveDataUserProfile.observe(viewLifecycleOwner){
                if (it != null) {
                   // token = "Barier ${it.token}"
                   // viewModel.getDataProfile(token)
                    binding.etFullNameBaru.setText(it.user.fullName)
                    binding.etEmailBaru.setText(it.user.email)
                    binding.etUsernameBaru.setText(it.user.username)
                    binding.etNomorTeleponBaru.setText(it.user.phoneNumber)
                }
            }
    }

    private fun updateProfileUser() {
        val fullName = binding.etFullNameBaru.text.toString()
        val phoneNumber = binding.etNomorTeleponBaru.text.toString()
        val email = binding.etEmailBaru.text.toString()
        val username = binding.etUsernameBaru.text.toString()


        if(fullName.isNotEmpty() && phoneNumber.isNotEmpty()){
            viewModel.updateDataProfile(token, PutDataUpdateProfile(fullName, email, username, phoneNumber, ""))

            viewModel.responseUpdateProfile.observe(viewLifecycleOwner){
                if(it != null){
                    Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                    binding.etFullNameBaru.setText(it.user.fullName)
                    binding.etEmailBaru.setText(it.user.email)
                    binding.etUsernameBaru.setText(it.user.username)
                    binding.etNomorTeleponBaru.setText(it.user.phoneNumber)

                }else{
                    Toast.makeText(context, "Update profile gagal!", Toast.LENGTH_SHORT).show()
                }
            }
        }else{
            Toast.makeText(context, "Data tidak boleh kosong!", Toast.LENGTH_SHORT).show()
        }



    }
}
