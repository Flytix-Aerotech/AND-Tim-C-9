package com.aerotech.flytix.view

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.aerotech.flytix.R
import com.aerotech.flytix.databinding.FragmentOtpBinding
import com.aerotech.flytix.viewmodel.OtpViewModel
import com.aerotech.flytix.viewmodel.RegisterViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Otp : Fragment() {
    lateinit var sharedPreferences: SharedPreferences
    lateinit var binding: FragmentOtpBinding
    lateinit var otpViewModel: OtpViewModel
    lateinit var userVM : RegisterViewModel
    private lateinit var setTimer: CountDownTimer

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentOtpBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        otpViewModel = ViewModelProvider(this).get(OtpViewModel::class.java)
        userVM = ViewModelProvider(this).get(RegisterViewModel::class.java)
        sharedPreferences = requireContext().getSharedPreferences("regist", Context.MODE_PRIVATE)
        val email = sharedPreferences.getString("email", "")
        binding.tvContentOtp2.text = "ke ${email}"
        binding.btnVerif.setOnClickListener {
            verifyOtp()
        }
        timer()

    }

    private fun verifyOtp() {
        val email = sharedPreferences.getString("email", "")
        val otp = binding.textIsiOtp.text.toString()
        if (otp.isEmpty()) {
            Toast.makeText(requireContext(), "Please fill all the field", Toast.LENGTH_SHORT).show()
        } else {
            otpViewModel.sendOVerifOtp(email!!, otp)
            otpViewModel.verifOtp.observe(viewLifecycleOwner) {
                if (it.msg == "OTP verified successfully") {
                    findNavController().navigate(R.id.action_otp_to_login2)
                    Toast.makeText(requireContext(), "Verifikasi Berhasil", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    Toast.makeText(requireContext(), "Maaf kode otp salah", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }
    private fun timer() {
        setTimer = object : CountDownTimer(60000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val timer = millisUntilFinished / 1000
                val timerText = "Kirim Ulang OTP dalam $timer detik"
                binding.tvKirimulang.text = timerText
            }

            @SuppressLint("SetTextI18n")
            override fun onFinish() {
                binding.tvKirimulang.text = "Kirim Ulang"
                binding.tvKirimulang.setTextColor(Color.GREEN)

                binding.tvKirimulang.isClickable = true

                binding.tvKirimulang.setOnClickListener {
                    resendOtp()
                    resetTimer()
                    Toast.makeText(requireContext(), "Kode OTP telah dikirim!", Toast.LENGTH_SHORT).show()
                }
            }
        }
        setTimer.start()

    }
    private fun resendOtp() {
        val email = sharedPreferences.getString("email", "")
        userVM.sendOtpRequest(email!!)
    }
    @SuppressLint("SetTextI18n")
    private fun resetTimer() {
        binding.tvKirimulang.isClickable = false
        binding.tvKirimulang.text = "Kirim Ulang OTP dalam 60 detik"
        binding.tvKirimulang.setTextColor(Color.WHITE)

        setTimer.cancel()
        timer()
    }
    override fun onDestroy() {
        super.onDestroy()
        setTimer.cancel()
    }

}