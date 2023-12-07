package com.faysalh.eproducts.model.productorder


import com.google.gson.annotations.SerializedName

data class ProductOrderDetail(
    @SerializedName("amount")
    val amount: Int,
    @SerializedName("created_at")
    val createdAt: Long,
    @SerializedName("price_item")
    val priceItem: Int,
    @SerializedName("product_id")
    val productId: Int,
    @SerializedName("product_name")
    val productName: String,
    @SerializedName("updated_at")
    val updatedAt: Long
)