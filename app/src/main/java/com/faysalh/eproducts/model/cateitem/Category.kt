package com.faysalh.eproducts.model.cateitem


import com.google.gson.annotations.SerializedName

data class Category(
    @SerializedName("brief")
    val brief: String,
    @SerializedName("color")
    val color: String,
    @SerializedName("icon")
    val icon: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
)