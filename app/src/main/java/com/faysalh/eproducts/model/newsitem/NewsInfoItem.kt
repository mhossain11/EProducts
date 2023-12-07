package com.faysalh.eproducts.model.newsitem


import com.google.gson.annotations.SerializedName

data class NewsInfoItem(
    @SerializedName("news_infos")
    val newsInfos: List<NewsInfo>,
    @SerializedName("status")
    val status: String
)