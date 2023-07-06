package com.aerotech.flytix.view.user

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
import com.aerotech.flytix.databinding.FragmentLoginBinding
import com.aerotech.flytix.model.user.DataUserLoginItem
import com.aerotech.flytix.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Login : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private lateinit var userLoginVM: LoginViewModel
    lateinit var sharedPref: SharedPreferences
    var token: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedPref = requireActivity().getSharedPreferences("LOGIN", Context.MODE_PRIVATE)
        userLoginVM = ViewModelProvider(this).get(LoginViewModel::class.java)

        binding.tvDftardisini.setOnClickListener {
            findNavController().navigate(R.id.action_login2_to_register)
        }
        initListener()
    }

    fun fiksLogin() {
        val emailInputUser = binding.etEmaillogin.text.toString()
        val passInputUser = binding.etPasslogin.text.toString()

        if (emailInputUser.isNotEmpty() && passInputUser.isNotEmpty()) {
            userLoginVM.authLoginUser(DataUserLoginItem(emailInputUser, passInputUser))
            userLoginVM.authLiveDataUserLogin.observe(viewLifecycleOwner) {
                if (it != null) {
                    val emailUser = it.user.email
                    val passUser = it.user.password
                    if (emailUser.isNotEmpty() && passUser.isNotEmpty()) {
                        Log.i("tokenn", "token: ${it.token}")
                        token = it.token
                        // input to sharedpreferences
                        sharedPref =
                            requireActivity().getSharedPreferences("LOGIN", Context.MODE_PRIVATE)
                        val userData = sharedPref.edit()
                        userData.putString("token", it.token)
                        userData.apply()
                        Toast.makeText(requireContext(), "Login Success", Toast.LENGTH_SHORT).show()
                        findNavController().navigate(R.id.action_login2_to_home3)
                    } else {
                        Toast.makeText(requireContext(), "Gagal Login", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }else{
            Toast.makeText(requireContext(), "Email dan Password Tidak Terdaftar", Toast.LENGTH_SHORT)
                .show()
        }

    }

    fun initListener() {
        binding.btnLogin.setOnClickListener {
            when {
                binding.etEmaillogin.text.isNullOrEmpty() -> binding.etEmaillogin.error =
                    " Email is required"

                binding.etPasslogin.text.isNullOrEmpty() -> binding.etPasslogin.error =
                    "Password is required"

                else -> {
                    fiksLogin()
                }
            }
        }
    }
}
