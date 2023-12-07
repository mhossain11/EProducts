package com.faysalh.eproducts.model.cateitem


import com.google.gson.annotations.SerializedName

data class CategoryItem(
    @SerializedName("categories")
    val categories: List<Category>,
    @SerializedName("status")
    val status: String
)