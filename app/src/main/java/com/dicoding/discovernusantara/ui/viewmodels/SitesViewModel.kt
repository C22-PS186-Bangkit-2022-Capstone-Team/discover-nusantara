package com.dicoding.discovernusantara.ui.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.discovernusantara.data.SitesRepository
import com.dicoding.discovernusantara.data.remote.ApiConfig
import com.dicoding.discovernusantara.data.remote.Prediction
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SitesViewModel(private val sitesRepository: SitesRepository): ViewModel() {
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _prediction = MutableLiveData<Prediction>()
    val prediction: LiveData<Prediction> = _prediction

    fun getAllSites() = sitesRepository.getAllSites()
    fun getSiteByName(name: String) = sitesRepository.getSitesByName(name)

    fun getPrediction(photo: MultipartBody.Part) {
        _isLoading.value = true
        ApiConfig.changeBaseUrl("https://get-prediction-qyqf4nfema-as.a.run.app")
        val client = ApiConfig.getApiService().getPrediction(photo)
        client.enqueue(object : Callback<Prediction> {
            override fun onResponse(call: Call<Prediction>, response: Response<Prediction>) {
                _isLoading.value = false
                if (response.body() != null) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        _prediction.value = responseBody!!
                    }
                } else {
                    _prediction.value = Prediction("")
                }
            }
            override fun onFailure(call: Call<Prediction>, t: Throwable) {
                _isLoading.value = false
                Log.e("SitesRepository", "ERROR")
            }
        })
    }
}