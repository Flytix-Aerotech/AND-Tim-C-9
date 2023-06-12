package com.aerotech.flytix.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.aerotech.flytix.R
import com.aerotech.flytix.databinding.FragmentRegisterBinding

class Register : Fragment() {
    private lateinit var binding: FragmentRegisterBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //userVM = ViewModelProvider(this).get(UserViewModel::class.java)
        binding.btnRegister.setOnClickListener {
//            register()
        }
    }

    private fun register() {
        val username = binding.namaLengkap.text.toString()
        val email = binding.emailRegister.text.toString()
        val nmrTlp = binding.nmrTlp.text.toString()
        val passwordConfirm = binding.passwordRegister.text.toString()

//        if (username.isEmpty() || email.isEmpty() || password.isEmpty() || passwordConfirm.isEmpty()) {
//            Toast.makeText(requireContext(), "Please fill all the field", Toast.LENGTH_SHORT).show()
//        } else {
//            if (password == passwordConfirm) {
//                userVM.postUserRegister(username, email, password)
//                Toast.makeText(requireContext(), "Registration Success", Toast.LENGTH_SHORT)
//                    .show()
//                findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
//            } else {
//                Toast.makeText(requireContext(), "Password not match", Toast.LENGTH_SHORT).show()
//            }
//        }
//    }


    }
}