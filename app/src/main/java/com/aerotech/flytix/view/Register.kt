package com.aerotech.flytix.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.aerotech.flytix.R
import com.aerotech.flytix.databinding.FragmentRegisterBinding
import com.aerotech.flytix.model.NewUser
import com.aerotech.flytix.viewmodel.RegisterViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class Register : Fragment() {
    lateinit var binding: FragmentRegisterBinding
    lateinit var userVM: RegisterViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentRegisterBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userVM = ViewModelProvider(this).get(RegisterViewModel::class.java)
        binding.btnRegister.setOnClickListener {
            register()
        }
        binding.masukdisini.setOnClickListener {
            findNavController().navigate(R.id.action_register_to_login2)
        }
    }

    private fun register() {
        val username = binding.username.text.toString()
        val email = binding.etEmailLogin.text.toString()
        val password = binding.passwordDaftar.text.toString()
        val fullName = binding.namaLengkap.text.toString()
        val noHp = binding.nmrTlp.text.toString()
        val alamat = binding.address.text.toString()
        //val currentDateTime: LocalDateTime = LocalDateTime.now()

        if (username.isEmpty() || email.isEmpty() || password.isEmpty() || fullName.isEmpty() || noHp.isEmpty() || alamat.isEmpty()) {
            Toast.makeText(requireContext(), "Please fill all the field", Toast.LENGTH_SHORT).show()
        } else {
            userVM.postUserRegister(
                dataUsers = NewUser(
                    email,
                    fullName,
                    password,
                    "user",
                    noHp,
                    "",
                    username,
                    alamat
                )
            )
            Toast.makeText(requireContext(), "Registration Success", Toast.LENGTH_SHORT)
                .show()
            findNavController().navigate(R.id.action_register_to_login2)

        }
    }
}