package com.test.techmint.fragment

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.test.techmint.R

class SplashFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?, ): View? {
        setTimer()
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    private fun setTimer() {
        Handler().postDelayed({
            findNavController().navigate(R.id.action_splashFragment_to_homeFragment)
        }, 2000)
    }
}