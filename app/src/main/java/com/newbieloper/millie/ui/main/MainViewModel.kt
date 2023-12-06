package com.newbieloper.millie.ui.main

import androidx.lifecycle.viewModelScope
import com.newbieloper.millie.core.base.LoadingViewModel
import com.newbieloper.millie.data.Result
import com.newbieloper.millie.data.remote.article.ArticleRepository
import com.newbieloper.millie.data.successOr
import com.newbieloper.millie.ui.main.model.Article
import com.newbieloper.millie.ui.main.model.toArticle
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val articleRepository: ArticleRepository
) : LoadingViewModel() {

    private val _showSplashScreen = MutableStateFlow(true)
    val showSplashScreen = _showSplashScreen.asStateFlow()

    private val _articleList = MutableStateFlow<List<Article>>(listOf())
    val articleList = _articleList.asStateFlow()

    init {
        getTopHeadlineArticleList()
    }

    private fun hideSplashScreen() {
        viewModelScope.launch {
            _showSplashScreen.emit(false)
        }
    }

    private fun getTopHeadlineArticleList() {
        viewModelScope.launch {
            articleRepository.getTopHeadlineArticleList().collectLatest { result ->
                when (result) {
                    is Result.Success -> {
                        val response = result.successOr(null)
                        if (response != null) {
                            _articleList.emit(response.articleList.map { it.toArticle() })
                        }
                    }

                    is Result.ErrorResponse -> {
                        // 에러 처리
                        Timber.e(result.errorBody.toString())
                    }

                    else -> {
                        // 에러 처리
                        Timber.e(result.toString())
                    }
                }

                hideSplashScreen()
            }
        }
    }
}