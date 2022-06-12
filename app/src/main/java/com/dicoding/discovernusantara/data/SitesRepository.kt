package com.dicoding.discovernusantara.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import androidx.sqlite.db.SimpleSQLiteQuery
import com.dicoding.discovernusantara.data.db.SitesDao
import com.dicoding.discovernusantara.data.db.SitesEntity
import com.dicoding.discovernusantara.data.remote.ApiConfig
import com.dicoding.discovernusantara.data.remote.ApiService
import java.lang.Exception

class SitesRepository private constructor(
    private val apiService: ApiService,
    private val sitesDao: SitesDao
){
    fun getAllSites(): LiveData<Result<List<SitesEntity>>> = liveData {
        emit(Result.Loading)
        try {
            ApiConfig.changeBaseUrl("https://pro-gecko-349214-ki3uf5q62a-as.a.run.app/")
            val response = apiService.getMonuments()
            val sitesList = response.map { sites ->
                SitesEntity(sites.id, sites.nama, sites.kota, sites.provinsi, sites.deskripsi, sites.imgURL, sites.latitude, sites.longitude)
            }
            sitesDao.deleteAll()
            sitesDao.insert(sitesList)
        } catch (e: Exception) {
            Log.e("SitesRepository", "getAllSites: ${e.message.toString()}")
        }
        val localData: LiveData<Result<List<SitesEntity>>> = sitesDao.getAllSites().map {
            Result.Success(it)
        }
        emitSource(localData)
    }

    fun getSitesByName(name: String): LiveData<List<SitesEntity>> = sitesDao.getSitesByName(name)

    companion object {
        private var INSTANCE: SitesRepository? = null
        fun getInstance(apiService: ApiService, sitesDao: SitesDao): SitesRepository =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: SitesRepository(apiService, sitesDao)
            }.also { INSTANCE = it }
    }
}