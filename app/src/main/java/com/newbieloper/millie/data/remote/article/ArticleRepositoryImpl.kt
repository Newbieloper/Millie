package com.newbieloper.millie.data.remote.article

import com.google.gson.Gson
import com.newbieloper.millie.BuildConfig
import com.newbieloper.millie.data.Result
import com.newbieloper.millie.data.remote.BaseRepository
import com.newbieloper.millie.data.remote.article.model.ArticleListResponse
import com.newbieloper.millie.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
class ArticleRepositoryImpl @Inject constructor(
    @IoDispatcher val ioDispatcher: CoroutineDispatcher,
    gson: Gson,
    private val articleService: ArticleService
) : BaseRepository(ioDispatcher, gson), ArticleRepository {

    override suspend fun getTopHeadlineArticleList(): Flow<Result<ArticleListResponse>> {
        return callToFlow(articleService.getTopHeadlineArticleList("kr", BuildConfig.API_KEY)).flatMapLatest { result ->
            flowOf(result)
        }
    }
}