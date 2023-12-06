package com.newbieloper.millie.data.repository.article

import com.newbieloper.millie.ui.main.model.Article
import kotlinx.coroutines.flow.Flow

interface ArticleRepository {

    suspend fun getTopHeadlineArticleList(): Flow<List<Article>>

    suspend fun updateArticle(article: Article)
}