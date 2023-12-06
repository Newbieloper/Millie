package com.newbieloper.millie.data.repository.article

import com.google.gson.Gson
import com.newbieloper.millie.BuildConfig
import com.newbieloper.millie.data.Result
import com.newbieloper.millie.data.local.article.ArticleDao
import com.newbieloper.millie.data.remote.article.ArticleService
import com.newbieloper.millie.data.repository.BaseRepository
import com.newbieloper.millie.data.successOr
import com.newbieloper.millie.di.IoDispatcher
import com.newbieloper.millie.ui.main.model.Article
import com.newbieloper.millie.ui.main.model.toArticle
import com.newbieloper.millie.ui.main.model.toArticleEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.withContext
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
class ArticleRepositoryImpl @Inject constructor(
    @IoDispatcher val ioDispatcher: CoroutineDispatcher,
    gson: Gson,
    private val articleService: ArticleService,
    private val articleDao: ArticleDao,
) : BaseRepository(ioDispatcher, gson), ArticleRepository {

    override suspend fun getTopHeadlineArticleList(): Flow<List<Article>> {
        return try {
            callToFlow(articleService.getTopHeadlineArticleList("kr", BuildConfig.API_KEY)).flatMapLatest { result ->
                flowOf(withContext(ioDispatcher) {

                    when (result) {
                        is Result.Success -> {
                            // 정상적으로 호출 성공 시 DB에 기록 후 데이터 방출
                            result.successOr(null)?.let { response ->
                                // 읽음 처리 이후 DB에 기록
                                val localReadArticleList = articleDao.getArticleList().map { it.toArticle() }.filter { it.isRead }
                                val remoteArticleList = response.articleList.map { entity ->
                                    return@map if (localReadArticleList.any { it.url == entity.url && it.isRead }) {
                                        entity.copy(isRead = true)
                                    } else {
                                        entity
                                    }
                                }
                                articleDao.insertAllArticleList(remoteArticleList)
                                remoteArticleList.map { it.toArticle() }
                            } ?: listOf()
                        }

                        else -> {
                            // 에러 발생 시 DB에 기록된 데이터 방출
                            articleDao.getArticleList().map { it.toArticle() }
                        }
                    }
                })
            }
        } catch (e: Exception) {
            // 에러 발생 시 DB에 기록된 데이터 방출
            flowOf(withContext(ioDispatcher) {
                articleDao.getArticleList().map { it.toArticle() }
            })
        }
    }

    override suspend fun updateArticle(article: Article) {
        flowOf(withContext(ioDispatcher) {
            articleDao.updateArticle(article.toArticleEntity())
        })
    }
}