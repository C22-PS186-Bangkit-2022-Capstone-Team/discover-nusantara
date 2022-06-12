package com.dicoding.discovernusantara.ui.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dicoding.discovernusantara.data.SitesRepository
import com.dicoding.discovernusantara.di.Injection
import java.lang.IllegalArgumentException

class ViewModelFactory private constructor(private val sitesRepository: SitesRepository): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        if (modelClass.isAssignableFrom(SitesViewModel::class.java)) {
            return SitesViewModel(sitesRepository) as T
        }
        throw IllegalArgumentException("Unknown view model: " + modelClass.name)
    }

    companion object {
        @Volatile
        private var INSTANCE: ViewModelFactory? = null
        fun getInstance(context: Context): ViewModelFactory =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: ViewModelFactory(Injection.provideRepository(context))
            }.also { INSTANCE = it }
    }
}