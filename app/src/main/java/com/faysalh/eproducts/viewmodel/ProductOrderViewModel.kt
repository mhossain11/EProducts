package com.faysalh.eproducts.viewmodel

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.faysalh.eproducts.model.productitem.ProductItem
import com.faysalh.eproducts.model.productorder.OrderModel
import com.faysalh.eproducts.model.productorder.ProductOrder
import com.faysalh.eproducts.model.productorder.ProductOrderDetail
import com.faysalh.eproducts.retroservice.RetroServiceInterface
import com.faysalh.eproducts.retroservice.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductOrderViewModel:ViewModel() {

    var liveData:MutableLiveData<OrderModel?> = MutableLiveData()

    fun getProductOrderLiveDataObserve(): MutableLiveData<OrderModel?> {
        return liveData
    }


    fun  getProduct(orderModel: OrderModel){
        val retroInstance = RetrofitInstance.getRetrofit
        val retrofitService = retroInstance.create(RetroServiceInterface::class.java)

        retrofitService.postProductOrder(orderModel).enqueue(object : Callback<OrderModel?> {
            override fun onResponse(call: Call<OrderModel?>, response: Response<OrderModel?>) {

                liveData.postValue(response.body())
                Log.e("PRODUCT_OK", response.body().toString(),)
            //            try {
//                   if (response.body()?.productOrder?.status.equals("success")) {
//                       liveData.postValue(response.body())
//                       Log.e("PRODUCT_OK", response.body().toString(),)
//                   } else {
//                       Log.e("PRODUCT_Failed", response.body().toString(),)
//                   }
//               }catch (e:Exception){}
            }

            override fun onFailure(call: Call<OrderModel?>, t: Throwable) {
                liveData.postValue(null)
                Log.e("PRODUCT_ERROR", t.localizedMessage, )
            }
        })
    }


}