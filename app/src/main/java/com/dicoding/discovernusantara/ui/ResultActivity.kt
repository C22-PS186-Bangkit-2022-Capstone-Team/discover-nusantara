package com.dicoding.discovernusantara.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.discovernusantara.data.db.SitesEntity
import com.dicoding.discovernusantara.databinding.ActivityResultBinding
import com.dicoding.discovernusantara.ui.adapter.SitesAdapter

class ResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val site = intent.getParcelableExtra<SitesEntity>(EXTRA_SITES) as SitesEntity
        val listSite = mutableListOf<SitesEntity>()
        listSite.add(site)

        val siteAdapter = SitesAdapter()
        siteAdapter.submitList(listSite)
        binding.rvSitesResult.apply {
            layoutManager = LinearLayoutManager(this@ResultActivity)
            setHasFixedSize(true)
            adapter = siteAdapter
        }
    }

    companion object {
        const val EXTRA_SITES = "extra_sites"
    }
}