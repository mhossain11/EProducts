package com.faysalh.eproducts.model.productitem



import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity(tableName = "productxtbl")
data class Product(
    @PrimaryKey
    @SerializedName("id")
    val id: String,
    @SerializedName("image")
    val image: String? =null,
    @SerializedName("name")
    val name: String? =null,
    @SerializedName("price")
    val price: String? =null,
    @SerializedName("price_discount")
    val priceDiscount: String? =null,
    @SerializedName("status")
    val status: String? =null,
    @SerializedName("stock")
    val stock: String? =null,
    var quantity:Int?=null
)