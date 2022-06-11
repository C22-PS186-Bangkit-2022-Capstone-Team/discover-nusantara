package com.dicoding.discovernusantara.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [SitesEntity::class], version = 1)
abstract class SitesRoomDatabase: RoomDatabase() {
    abstract fun sitesDao(): SitesDao

    companion object {
        @Volatile
        private var INSTANCE: SitesRoomDatabase? = null

        @JvmStatic
        fun getDatabase(context: Context): SitesRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    SitesRoomDatabase::class.java, "site_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { INSTANCE = it }
            }
        }
    }
}