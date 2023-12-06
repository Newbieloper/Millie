package com.newbieloper.millie.data.remote.article.model

import com.google.gson.annotations.SerializedName
import com.newbieloper.millie.data.entity.Article

data class ArticleListResponse(
    @SerializedName("status") val status: String,
    @SerializedName("totalResults") val totalResults: Int,
    @SerializedName("articles") val articleList: List<Article>
)