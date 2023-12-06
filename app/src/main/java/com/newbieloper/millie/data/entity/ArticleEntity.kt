package com.newbieloper.millie.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.time.ZonedDateTime

@Entity(tableName = "Article")
data class ArticleEntity(
    @PrimaryKey(autoGenerate = false)
    @SerializedName("url")
    val url: String,
    @SerializedName("title") val title: String,
    @SerializedName("author") val author: String?,
    @SerializedName("content") val content: String?,
    @SerializedName("description") val description: String?,
    @SerializedName("urlToImage") val urlToImage: String?,
    @SerializedName("publishedAt") val publishedAt: ZonedDateTime,
    @SerializedName("source") val source: SourceEntity,
    @SerializedName("read") val isRead: Boolean = false
)

data class SourceEntity(
    @SerializedName("id") val id: String?,
    @SerializedName("name") val name: String
)