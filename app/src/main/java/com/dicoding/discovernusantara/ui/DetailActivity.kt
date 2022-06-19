package com.dicoding.discovernusantara.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.dicoding.discovernusantara.data.Sites
import com.dicoding.discovernusantara.data.db.SitesEntity
import com.dicoding.discovernusantara.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val site = intent.getParcelableExtra<SitesEntity>(EXTRA_SITES) as SitesEntity
        binding.txtNameDetail.text = site.name
        binding.txtLocationDetail.text = "${site.city}, ${site.province}"
        binding.textDescription.text = site.description
        Glide.with(this)
            .load(site.imageUrl)
            .into(binding.imgDetail)

        binding.btnShowMap.setOnClickListener {
            val intent = Intent(this, MapsActivity::class.java)
            intent.putExtra(MapsActivity.EXTRA_TITLE, site.name)
            intent.putExtra(MapsActivity.EXTRA_LAT, site.long)
            intent.putExtra(MapsActivity.EXTRA_LONG, site.lat)
            startActivity(intent)
        }

        binding.imgBack.setOnClickListener {
            onBackPressed()
        }
    }

    companion object {
        const val EXTRA_SITES = "extra_sites"
    }
}