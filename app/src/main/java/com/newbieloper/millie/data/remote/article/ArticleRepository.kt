package com.newbieloper.millie.data.remote.article

import com.newbieloper.millie.data.Result
import com.newbieloper.millie.data.remote.article.model.ArticleListResponse
import kotlinx.coroutines.flow.Flow

interface ArticleRepository {

    suspend fun getTopHeadlineArticleList(): Flow<Result<ArticleListResponse>>
}