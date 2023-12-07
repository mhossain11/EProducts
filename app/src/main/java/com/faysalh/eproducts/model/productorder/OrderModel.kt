package com.faysalh.eproducts.model.productorder


import com.google.gson.annotations.SerializedName

data class OrderModel(
    @SerializedName("product_order")
    val productOrder: ProductOrder,
    @SerializedName("product_order_detail")
    val productOrderDetail: ProductOrderDetail
)