package com.aerotech.flytix.view.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.aerotech.flytix.R
import com.aerotech.flytix.databinding.FragmentForgetPasswordBinding
import com.aerotech.flytix.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ForgetPassword : Fragment() {
    lateinit var binding: FragmentForgetPasswordBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentForgetPasswordBinding.inflate(inflater, container, false)
        return binding.root
    }


}