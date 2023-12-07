package com.faysalh.eproducts.model.descripitem


import com.google.gson.annotations.SerializedName

data class DescriptionProducrItem(
    @SerializedName("product")
    val product: ProductX,
    @SerializedName("status")
    val status: String
)