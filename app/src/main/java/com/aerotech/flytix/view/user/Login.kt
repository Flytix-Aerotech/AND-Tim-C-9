package com.aerotech.flytix.view.user

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.aerotech.flytix.R
import com.aerotech.flytix.databinding.FragmentLoginBinding
import com.aerotech.flytix.model.DataUserLoginItem
import com.aerotech.flytix.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class Login : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private lateinit var userVM: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userVM = ViewModelProvider(this).get(UserViewModel::class.java)
        binding.btnLogin.setOnClickListener {
            prosesLogin()
        }
    }

    private fun prosesLogin() {
        val email = binding.etEmaillogin.text.toString()
        val password = binding.etPasslogin.text.toString()
        val requestLogin = DataUserLoginItem(email, password)

        if (email.isNotEmpty() || password.isNotEmpty()) {
            userVM.userLogin(requestLogin) { loginResult ->
                if (loginResult.token.isNotEmpty()) {
                    Toast.makeText(requireContext(), loginResult.message, Toast.LENGTH_SHORT).show()
                    findNavController().navigate(R.id.action_login2_to_updateProfile2)
                } else {
                    Toast.makeText(requireContext(), loginResult.message, Toast.LENGTH_SHORT).show()
                }
            }
        } else {
            Toast.makeText(requireContext(), "Please enter email and password", Toast.LENGTH_SHORT).show()
        }
    }
}