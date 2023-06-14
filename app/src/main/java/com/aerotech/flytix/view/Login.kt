package com.aerotech.flytix.view

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.aerotech.flytix.R
import com.aerotech.flytix.databinding.FragmentLoginBinding
import com.aerotech.flytix.model.DataUserLoginItem
import com.aerotech.flytix.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Login : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private lateinit var userLoginVM: LoginViewModel
    lateinit var sharedPref: SharedPreferences
    var token: String? = null
    var emailUser: String? = null
    var passUser: String? = null

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
        userLoginVM = ViewModelProvider(this).get(LoginViewModel::class.java)
        sharedPref = requireContext().getSharedPreferences("LOGGED_IN", Context.MODE_PRIVATE)
        binding.btnLogin.setOnClickListener {
            prosesLogin()
        }

        binding.tvDftardisini.setOnClickListener {
            findNavController().navigate(R.id.action_login2_to_register)
        }
    }

    private fun prosesLogin() {
        val email = binding.etEmaillogin.text.toString()
        val password = binding.etPasslogin.text.toString()


        if (email.isNotEmpty() || password.isNotEmpty()) {
            userLoginVM.authLogin()
            userLoginVM.livedatauserLogin.observe(viewLifecycleOwner) {
                emailUser = it.email
                passUser = it.password
                emailUser = email
                passUser = password
            }

            if (emailUser != email && passUser != password) {
                Toast.makeText(requireContext(), "Gagal Login", Toast.LENGTH_SHORT).show()
            } else {
//            if (emailUser == email && passUser == password) {
                userLoginVM.userLogin(DataUserLoginItem(email, password))
            userLoginVM.dataPostLoginUser.observe(viewLifecycleOwner) { loginresult ->
//                    emailUser = loginresult.user.email
//                    passUser = loginresult.user.password
//                    emailUser = email
//                    passUser = password
//                if (emailUser != email && passUser != password) {
//                    Toast.makeText(requireContext(), "Gagal Login", Toast.LENGTH_SHORT).show()
//                } else {
                    Toast.makeText(requireContext(), loginresult.message, Toast.LENGTH_SHORT)
                        .show()
                    findNavController().navigate(R.id.action_login2_to_home3)
                }
            }
//            } else {
//                Toast.makeText(requireContext(), "Gagal Login", Toast.LENGTH_SHORT).show()
//            }
        } else {
            Toast.makeText(requireContext(), "Please enter email and password", Toast.LENGTH_SHORT)
                .show()
        }
    }

//    fun doLogin(){
//        userLoginVM.authLogin(DataUserLoginItem(binding.etEmaillogin.text.toString(),binding.etEmaillogin.text.toString()))
//        userLoginVM.userLogin.observe(viewLifecycleOwner){
//            if (it!=null){
//                Log.i("tokenn", "token: ${it.token}")
//                token =it.token
//                // input to sharedpreferences
//                val userData = sharedPref.edit()
//                userData.putString("token", it.token)
//                userData.apply()
//                findNavController().navigate(R.id.action_login2_to_home2)
//            }
//        }
//    }
}