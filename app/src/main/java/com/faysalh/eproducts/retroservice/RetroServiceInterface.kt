package com.faysalh.eproducts.retroservice

import com.faysalh.eproducts.model.cateitem.Category
import com.faysalh.eproducts.model.cateitem.CategoryItem
import com.faysalh.eproducts.model.descripitem.DescriptionProducrItem
import com.faysalh.eproducts.model.productitem.ProductItem
import com.faysalh.eproducts.model.newsitem.NewsInfoItem
import com.faysalh.eproducts.model.productorder.OrderModel
import com.faysalh.eproducts.model.productorder.ProductOrder
import com.faysalh.eproducts.model.productorder.ProductOrderDetail
import com.faysalh.eproducts.utils.GET_CATEGORIES_URL
import com.faysalh.eproducts.utils.GET_OFFERS_URL
import com.faysalh.eproducts.utils.GET_PRODUCTS_URL
import com.faysalh.eproducts.utils.GET_PRODUCT_DETAILS_URL
import com.faysalh.eproducts.utils.POST_ORDER_URL
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface RetroServiceInterface {

    @GET(GET_CATEGORIES_URL)
    fun getCategory():Call<CategoryItem>

    @GET(GET_PRODUCTS_URL)
    fun getProduct():Call<ProductItem>

    @GET(GET_OFFERS_URL )
    fun  getNewSInfo():Call<NewsInfoItem>

    @GET(GET_PRODUCT_DETAILS_URL)
    fun getProductDetails(@Query("id")id:String):Call<DescriptionProducrItem>


    @Headers("Security:secure_code")
    @POST(POST_ORDER_URL)
    fun postProductOrder(@Body orderModel: OrderModel):Call<OrderModel>
}