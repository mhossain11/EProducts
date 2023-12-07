package com.faysalh.eproducts.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.faysalh.eproducts.model.productitem.ProductItem
import com.faysalh.eproducts.retroservice.RetroServiceInterface
import com.faysalh.eproducts.retroservice.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductViewModel:ViewModel() {

    var liveData:MutableLiveData<ProductItem?> = MutableLiveData()

    fun getProductLiveDataObserve(): MutableLiveData<ProductItem?> {
        return liveData
    }


    fun  getProduct(){
        val retroInstance = RetrofitInstance.getRetrofit
        val retrofitService = retroInstance.create(RetroServiceInterface::class.java)

        retrofitService.getProduct().enqueue(object : Callback<ProductItem?> {
            override fun onResponse(call: Call<ProductItem?>, response: Response<ProductItem?>) {
                if (response.body()?.status.equals("success")){
                    liveData.postValue(response.body())
                    Log.e("PRODUCT_OK", response.body().toString(), )
                }
            }

            override fun onFailure(call: Call<ProductItem?>, t: Throwable) {
                liveData.postValue(null)
                Log.e("PRODUCT_ERROR", t.localizedMessage, )
            }
        })
    }


}