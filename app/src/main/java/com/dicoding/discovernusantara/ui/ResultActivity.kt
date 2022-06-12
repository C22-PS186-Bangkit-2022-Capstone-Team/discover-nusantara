package com.dicoding.discovernusantara.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.discovernusantara.data.db.SitesEntity
import com.dicoding.discovernusantara.databinding.ActivityResultBinding
import com.dicoding.discovernusantara.ui.adapter.SitesAdapter
import com.dicoding.discovernusantara.ui.viewmodels.SitesViewModel
import com.dicoding.discovernusantara.ui.viewmodels.ViewModelFactory

class ResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBackResult.setOnClickListener { onBackPressed() }

        val factory: ViewModelFactory = ViewModelFactory.getInstance(this)
        val viewModel: SitesViewModel by viewModels { factory }

        val site = intent.getStringExtra(EXTRA_SITES)
        Log.d("ResultActivity", site.toString())
        viewModel.getSiteByName(site.toString()).observe(this) {
            Log.d("ResultActivity", it.toString())
            val siteAdapter = SitesAdapter()
            siteAdapter.submitList(it)
            binding.rvSitesResult.apply {
                layoutManager = LinearLayoutManager(this@ResultActivity)
                setHasFixedSize(true)
                adapter = siteAdapter
            }
        }
    }

    companion object {
        const val EXTRA_SITES = "extra_sites"
    }
}