package com.dicoding.discovernusantara.di

import android.content.Context
import com.dicoding.discovernusantara.data.SitesRepository
import com.dicoding.discovernusantara.data.db.SitesRoomDatabase
import com.dicoding.discovernusantara.data.remote.ApiConfig

object Injection {
    fun provideRepository(context: Context): SitesRepository {
        val apiConfig = ApiConfig
        val database = SitesRoomDatabase.getDatabase(context)
        val dao = database.sitesDao()
        return SitesRepository.getInstance(apiConfig, dao)
    }
}