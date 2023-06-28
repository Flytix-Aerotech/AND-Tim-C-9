package com.aerotech.flytix.view

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.aerotech.flytix.R
import com.aerotech.flytix.databinding.FragmentForgetPasswordBinding
import com.aerotech.flytix.viewmodel.ResetPasswordViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ForgetPassword : Fragment() {
    lateinit var binding: FragmentForgetPasswordBinding
    lateinit var sharedPreferences: SharedPreferences
    lateinit var ResetPassVM: ResetPasswordViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentForgetPasswordBinding.inflate(layoutInflater, container , false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedPreferences = requireActivity().getSharedPreferences("regist", Context.MODE_PRIVATE)
        ResetPassVM = ViewModelProvider(this).get(ResetPasswordViewModel::class.java)
        binding.btnGantiPassword.setOnClickListener {
            gantiPassword()
        }
    }
    private fun gantiPassword(){
        val email = sharedPreferences.getString("email","")
        val password = binding.etPasswordBaru.text.toString()
        val confPass = binding.etPasswordBaru.text.toString()
        if(password.isEmpty() || confPass.isEmpty()) {
            Toast.makeText(requireContext(), "Please fill all the field", Toast.LENGTH_SHORT).show()
        }else if (password != confPass){
            Toast.makeText(requireContext(), "password dan konfirmasi password anda tidak sama", Toast.LENGTH_SHORT).show()
        }else{
            ResetPassVM.putResetPassword(email!!, password, confPass)
            findNavController().navigate(R.id.action_forgetPassword_to_login2)
        }
    }

}