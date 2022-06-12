package com.dicoding.discovernusantara.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteQuery

@Dao
interface SitesDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(sites: List<SitesEntity>)

    @Update
    suspend fun update(sites: SitesEntity)

    @Delete
    suspend fun delete(sites: SitesEntity)

    @Query("DELETE FROM sitesentity")
    suspend fun deleteAll()

    @Query("SELECT * FROM sitesentity ORDER BY city ASC")
    fun getAllSites(): LiveData<List<SitesEntity>>

    @Query("SELECT * FROM sitesentity WHERE name LIKE '%' || :name || '%'")
    fun getSitesByName(name: String): LiveData<List<SitesEntity>>
}