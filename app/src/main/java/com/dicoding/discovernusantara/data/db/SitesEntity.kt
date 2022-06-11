package com.dicoding.discovernusantara.data.db

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "newsentity")
@Parcelize
data class SitesEntity (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int = 0,

    @ColumnInfo(name = "name")
    val name: String? = null,

    @ColumnInfo(name = "city")
    val city: String? = null,

    @ColumnInfo(name = "province")
    val province: String? = null,

    @ColumnInfo(name = "description")
    val description: String? = null,

    @ColumnInfo(name = "imgUrl")
    val imageUrl: String? = null,

    @ColumnInfo(name ="lat")
    val lat: Double = 0.0,

    @ColumnInfo(name = "long")
    val long: Double = 0.0
): Parcelable