package com.dicoding.discovernusantara.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dicoding.discovernusantara.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnShowMap.setOnClickListener {

        }
    }

    companion object {
        const val EXTRA_SITES = "extra_sites"
    }
}