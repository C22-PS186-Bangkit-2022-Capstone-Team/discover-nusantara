package com.dicoding.discovernusantara.data.remote

import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ApiService {
    @GET("monument")
    suspend fun getMonuments(): List<SitesResponse>

    @Multipart
    @POST("/")
    fun getPrediction(
        @Part imagefile: MultipartBody.Part
    ): Call<Prediction>
}