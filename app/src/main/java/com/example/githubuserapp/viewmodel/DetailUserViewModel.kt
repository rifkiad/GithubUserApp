package com.example.githubuserapp.viewmodel

import android.os.Parcel
import android.os.Parcelable
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubuserapp.Api.ApiConfig
import com.example.githubuserapp.Api.DetailResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailUserViewModel() : ViewModel(), Parcelable {

    private val _detailUser = MutableLiveData<DetailResponse>()
    val detailUser: LiveData<DetailResponse> = _detailUser

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun detailUser(path: String) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getDetailUser(path)
        client.enqueue(object : Callback<DetailResponse> {
            override fun onResponse(
                call: Call<DetailResponse>,
                response: Response<DetailResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _detailUser.value = response.body()
                } else {
                    Log.e("DetailUserViewModel", "onfailure: Gagal")
                }
            }

            override fun onFailure(call: Call<DetailResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e("DetailUserViewModel", "onfailure: ${t.message.toString()}")
            }
        })
    }


    constructor(parcel: Parcel) : this() {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<DetailUserViewModel> {
        override fun createFromParcel(parcel: Parcel): DetailUserViewModel {
            return DetailUserViewModel(parcel)
        }

        override fun newArray(size: Int): Array<DetailUserViewModel?> {
            return arrayOfNulls(size)
        }
    }
}