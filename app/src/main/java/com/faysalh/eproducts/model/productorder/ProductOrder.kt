package com.faysalh.eproducts.model.productorder


import com.google.gson.annotations.SerializedName

data class ProductOrder(
    @SerializedName("address")
    val address: String,
    @SerializedName("buyer")
    val buyer: String,
    @SerializedName("comment")
    val comment: String,
    @SerializedName("created_at")
    val createdAt: Long?=null,
    @SerializedName("date_ship")
    val dateShip: Long,
    @SerializedName("email")
    val email: String,
    @SerializedName("phone")
    val phone: String,
    @SerializedName("serial")
    val serial: String?=null,
    @SerializedName("shipping")
    val shipping: String?=null,
    @SerializedName("shipping_location")
    val shippingLocation: String?=null,
    @SerializedName("shipping_rate")
    val shippingRate: String?=null,
    @SerializedName("status")
    val status: String?=null,
    @SerializedName("tax")
    val tax: Int?=null,
    @SerializedName("total_fees")
    val totalFees: Double,
    @SerializedName("updated_at")
    val updatedAt: Long?=null
)