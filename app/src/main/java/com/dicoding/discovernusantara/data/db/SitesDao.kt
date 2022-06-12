package com.dicoding.discovernusantara.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteQuery

@Dao
interface SitesDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(sites: SitesEntity)

    @Update
    fun update(sites: SitesEntity)

    @Delete
    fun delete(sites: SitesEntity)

    @Query("SELECT * FROM sitesentity ORDER BY name ASC")
    fun getAllSites(): LiveData<List<SitesEntity>>

    @Query("SELECT * FROM sitesentity WHERE name LIKE '%' || :name || '%'")
    fun getSitesByName(name: String): LiveData<List<SitesEntity>>
}