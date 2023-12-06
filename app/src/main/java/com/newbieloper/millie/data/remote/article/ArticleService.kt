package com.newbieloper.millie.data.remote.article

import com.newbieloper.millie.data.remote.article.model.ArticleListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ArticleService {

    /**
     * 뉴스 기사 리스트 조회
     */
    @GET("top-headlines")
    suspend fun getTopHeadlineArticleList(@Query("country") country : String, @Query("apiKey") apiKey : String): Response<ArticleListResponse>
}