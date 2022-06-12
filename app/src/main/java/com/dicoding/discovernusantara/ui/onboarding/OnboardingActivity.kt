package com.dicoding.discovernusantara.ui.onboarding

import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowInsets
import android.view.WindowManager
import com.dicoding.discovernusantara.R
import com.dicoding.discovernusantara.databinding.ActivityOnboardingBinding

class OnboardingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOnboardingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnboardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        hideSystemUI()

        binding.btnStart.setOnClickListener {
            savePreference()
            finish()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        savePreference()
    }

    private fun savePreference() {
        val sharedFile = packageName
        val shared: SharedPreferences = getSharedPreferences(sharedFile, Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = shared.edit()
        editor.putBoolean("shared_onboarding", true)
        editor.apply()
    }

    private fun hideSystemUI() {
        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
        supportActionBar?.hide()
    }
}