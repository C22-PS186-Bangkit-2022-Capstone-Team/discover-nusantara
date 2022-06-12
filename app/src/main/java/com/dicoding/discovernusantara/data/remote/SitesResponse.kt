package com.dicoding.discovernusantara.data.remote

import com.google.gson.annotations.SerializedName

data class SitesResponse(

	@field:SerializedName("Kota")
	val kota: String? = null,

	@field:SerializedName("Nama")
	val nama: String? = null,

	@field:SerializedName("Deskripsi")
	val deskripsi: String? = null,

	@field:SerializedName("Provinsi")
	val provinsi: String? = null,

	@field:SerializedName("Latitude")
	val latitude: Double = 0.0,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("Longitude")
	val longitude: Double = 0.0,

	@field:SerializedName("ImgURL")
	val imgURL: String? = null
)
