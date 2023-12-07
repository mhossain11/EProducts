package com.faysalh.eproducts.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.faysalh.eproducts.model.cateitem.Category
import com.faysalh.eproducts.model.cateitem.CategoryItem
import com.faysalh.eproducts.retroservice.RetroServiceInterface
import com.faysalh.eproducts.retroservice.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoryViewModel:ViewModel() {
    var liveData : MutableLiveData<CategoryItem?> = MutableLiveData()

    fun getCategoryLiveDataObserve(): MutableLiveData<CategoryItem?> {
        return  liveData
    }

    fun categoryAPI(){
        val retroInstance = RetrofitInstance.getRetrofit
        val retrofitService = retroInstance.create(RetroServiceInterface::class.java)

        retrofitService.getCategory().enqueue(object : Callback<CategoryItem> {
            override fun onResponse(
                call: Call<CategoryItem>,
                response: Response<CategoryItem>
            ) {
                Log.e("CATEGORY_OK", response.body().toString(), )
                if (response.body()?.status.equals("success")){
                    liveData.postValue(response.body())
                    Log.e("CATEGORY_OK", response.body().toString(), )
                }

            }

            override fun onFailure(call: Call<CategoryItem>, t: Throwable) {
                liveData.postValue(null)
                Log.e("CATEGORY_ERROR", t.localizedMessage, )
            }
        })
    }


}