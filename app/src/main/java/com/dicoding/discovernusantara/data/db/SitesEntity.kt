package com.dicoding.discovernusantara.data.db

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "sitesentity")
@Parcelize
data class SitesEntity (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0,

    @ColumnInfo(name = "name")
    var name: String? = null,

    @ColumnInfo(name = "city")
    var city: String? = null,

    @ColumnInfo(name = "province")
    var province: String? = null,

    @ColumnInfo(name = "description")
    var description: String? = null,

    @ColumnInfo(name = "imgUrl")
    var imageUrl: String? = null,

    @ColumnInfo(name ="lat")
    var lat: Double = 0.0,

    @ColumnInfo(name = "long")
    var long: Double = 0.0
): Parcelable