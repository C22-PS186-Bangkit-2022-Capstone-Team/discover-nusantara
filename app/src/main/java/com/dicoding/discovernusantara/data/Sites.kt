package com.dicoding.discovernusantara.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Sites(
    val name: String,
    val city: String,
    val province: String,
    val description: String,
    val imageUrl: String,
    val lat: Double,
    val long: Double
): Parcelable
