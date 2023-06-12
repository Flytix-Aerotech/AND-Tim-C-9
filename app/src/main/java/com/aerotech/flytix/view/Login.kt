package com.aerotech.flytix.view

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.aerotech.flytix.R
import com.aerotech.flytix.databinding.FragmentLoginBinding
import com.aerotech.flytix.model.NewUser
import com.aerotech.flytix.network.RetrofitClient
import com.aerotech.flytix.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Login : Fragment() {
    lateinit var binding: FragmentLoginBinding
    lateinit var userVM: UserViewModel
    lateinit var sharepref : SharedPreferences

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
        sharepref = requireContext().getSharedPreferences("LOGGED_IN" , Context.MODE_PRIVATE)

        binding.btnLogin.setOnClickListener {
            prosesLogin()
        }

        binding.tvBlmPunyaAkun.setOnClickListener {
            //findNavController().navigate(R.id.)
        }
    }
    lateinit var listUserLogin: List<NewUser>

    fun prosesLogin(){
        userVM = ViewModelProvider(this).get(UserViewModel::class.java)
        userVM.dataLoginUser.observe(viewLifecycleOwner, {
            listUserLogin = it
        })
        userVM.UserLogin()
    }

}