package com.faysalh.eproducts.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.faysalh.eproducts.model.newsitem.NewsInfoItem
import com.faysalh.eproducts.retroservice.RetroServiceInterface
import com.faysalh.eproducts.retroservice.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsInfoViewModel:ViewModel() {

    private var liveData :MutableLiveData<NewsInfoItem?> = MutableLiveData()

    fun getNewsInfoLiveDataObserve(): MutableLiveData<NewsInfoItem?> {
        return liveData

    }

    fun getNewsInfo(){
        val retroInstance = RetrofitInstance.getRetrofit
        val retrofitService = retroInstance.create(RetroServiceInterface::class.java)
        retrofitService.getNewSInfo().enqueue(object : Callback<NewsInfoItem?> {
            override fun onResponse(call: Call<NewsInfoItem?>, response: Response<NewsInfoItem?>) {
                if (response.body()?.status.equals("success")){
                    Log.e("NEWSINFO_Ok", response.body().toString(), )
                    liveData.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<NewsInfoItem?>, t: Throwable) {
                liveData.postValue(null)
                Log.e("NEWSINFO_ERROR", t.localizedMessage, )

            }
        })
    }
}