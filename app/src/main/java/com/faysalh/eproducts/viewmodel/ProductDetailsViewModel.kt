package com.faysalh.eproducts.viewmodel


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.faysalh.eproducts.db.ProductDatabase
import com.faysalh.eproducts.model.descripitem.DescriptionProducrItem
import com.faysalh.eproducts.model.productitem.Product
import com.faysalh.eproducts.retroservice.RetroServiceInterface
import com.faysalh.eproducts.retroservice.RetrofitInstance
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class  ProductDetailsViewModel(val productDatabase: ProductDatabase):ViewModel(){

    val liveData : MutableLiveData<DescriptionProducrItem?> = MutableLiveData()
    private val productAddLivedata =productDatabase.productDao().getAllMeals()
    fun getProductDetailsLiveDataObserve(): MutableLiveData<DescriptionProducrItem?> {
        return liveData
    }

    fun getProductDetails(id:String) {

        val retroInstance = RetrofitInstance.getRetrofit
        val retrofitService = retroInstance.create(RetroServiceInterface::class.java)

        retrofitService.getProductDetails(id).enqueue(object : Callback<DescriptionProducrItem?> {
            override fun onResponse(
                call: Call<DescriptionProducrItem?>,
                response: Response<DescriptionProducrItem?>
            ) {
                if (response.body()?.status.equals("success")){
                    Log.e("ProductDetails_Ok", response.body().toString(), )
                    liveData.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<DescriptionProducrItem?>, t: Throwable) {
                liveData.postValue(null)
                Log.e("ProductDetails_ERROR", t.localizedMessage, )

            }

        })
    }


    fun insertProduct (product: Product){
        viewModelScope.launch {
            productDatabase.productDao().update(product)
        }

    }

    fun deleteProduct(product: Product){
        viewModelScope.launch {
            productDatabase.productDao().delete(product)
        }
    }
    fun observeProductAddLivedata(): LiveData<List<Product>> {
        return productAddLivedata
    }

}