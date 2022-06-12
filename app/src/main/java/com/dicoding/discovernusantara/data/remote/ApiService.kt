package com.dicoding.discovernusantara.data.remote

import retrofit2.http.GET

interface ApiService {
    @GET("monument")
    fun getMonuments()
}