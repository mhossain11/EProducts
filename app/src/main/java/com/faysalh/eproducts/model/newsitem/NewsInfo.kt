package com.faysalh.eproducts.model.newsitem


import com.google.gson.annotations.SerializedName

data class NewsInfo(

    @SerializedName("image")
    val image: String,
    @SerializedName("title")
    val title: String
)