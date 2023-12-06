package com.newbieloper.millie.data.entity

import com.google.gson.annotations.SerializedName
import java.time.ZonedDateTime

data class Article(
    @SerializedName("title") val title: String,
    @SerializedName("author") val author: String?,
    @SerializedName("content") val content: String?,
    @SerializedName("description") val description: String?,
    @SerializedName("url") val url: String,
    @SerializedName("urlToImage") val urlToImage: String?,
    @SerializedName("publishedAt") val publishedAt: ZonedDateTime,
    @SerializedName("source") val source: Source
)

data class Source(
    @SerializedName("id") val id: String?,
    @SerializedName("name") val name: String
)