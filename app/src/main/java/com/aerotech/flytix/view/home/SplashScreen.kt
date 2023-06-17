package com.aerotech.flytix.view.home

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.aerotech.flytix.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
@Suppress("DEPRECATION")
class SplashScreen : Fragment() {
    private lateinit var progressBar: ProgressBar
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash_screen, container, false)

    }

    override fun onResume() {
        super.onResume()
        // Menjalankan tugas yang memakan waktu di sini
        simulateLoading()
    }

    private fun simulateLoading() {
        progressBar = requireView().findViewById(R.id.progressBar)
        val handler = Handler()
        val progressRunnable = Runnable {
            var progress = 0
            while (progress <= 1000) {
                progressBar.progress = progress
                progress += 10
                handler.postDelayed({ }, 1000)
            }
            findNavController().navigate(R.id.action_splashScreen_to_register)
        }
        handler.postDelayed(progressRunnable, 3000)
    }

}