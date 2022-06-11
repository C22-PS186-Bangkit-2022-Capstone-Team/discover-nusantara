package com.dicoding.discovernusantara.data

import androidx.lifecycle.LiveData
import androidx.sqlite.db.SimpleSQLiteQuery
import com.dicoding.discovernusantara.data.db.SitesDao
import com.dicoding.discovernusantara.data.db.SitesEntity

class SitesRepository private constructor(
    private val sitesDao: SitesDao
){
    fun getAllSites(): LiveData<List<SitesEntity>> = sitesDao.getAllSites()

    fun getSitesByName(name: String): LiveData<List<SitesEntity>> {
        val query = "SELECT * FROM sitesentity WHERE name LIKE '%$name%'"
        val simpleQuery = SimpleSQLiteQuery(query)
        return sitesDao.getSitesByName(simpleQuery)
    }
}