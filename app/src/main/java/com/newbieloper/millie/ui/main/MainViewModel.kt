package com.newbieloper.millie.ui.main

import androidx.lifecycle.viewModelScope
import com.newbieloper.millie.core.base.LoadingViewModel
import com.newbieloper.millie.data.repository.article.ArticleRepository
import com.newbieloper.millie.ui.main.model.Article
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
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
                _articleList.emit(result)
                hideSplashScreen()
            }
        }
    }

    fun readArticle(article: Article) {
        viewModelScope.launch {
            val readArticle = article.copy(isRead = true)
            articleRepository.updateArticle(readArticle)
            _articleList.emit(
                articleList.value.map {
                    if (it.url == readArticle.url) {
                        readArticle
                    } else {
                        it
                    }
                }
            )
        }
    }
}