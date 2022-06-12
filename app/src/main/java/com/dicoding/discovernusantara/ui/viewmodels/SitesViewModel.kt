package com.dicoding.discovernusantara.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.dicoding.discovernusantara.data.SitesRepository

class SitesViewModel(private val sitesRepository: SitesRepository): ViewModel() {
    fun getAllSites() = sitesRepository.getAllSites()
}