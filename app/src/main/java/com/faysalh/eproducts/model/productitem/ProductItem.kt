package com.faysalh.eproducts.model.productitem


import com.google.gson.annotations.SerializedName


data class ProductItem(
    @SerializedName("products")
    val products: List<Product>,
    @SerializedName("status")
    val status: String?
)