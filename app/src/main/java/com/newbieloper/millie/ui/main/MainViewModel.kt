package com.newbieloper.millie.ui.main

import androidx.lifecycle.viewModelScope
import com.newbieloper.millie.core.base.LoadingViewModel
import com.newbieloper.millie.ui.main.model.Article
import com.newbieloper.millie.ui.main.model.Source
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : LoadingViewModel() {

    private val _showSplashScreen = MutableStateFlow(true)
    val showSplashScreen = _showSplashScreen.asStateFlow()

    private val _articleList = MutableStateFlow<List<Article>>(listOf())
    val articleList = _articleList.asStateFlow()

    init {
        viewModelScope.launch {
            _articleList.emit(mutableListOf<Article>().apply {
                add(
                    Article(
                        title = "title",
                        author = "author",
                        content = "content",
                        description = "description",
                        url = "url",
                        urlToImage = "urlToImage",
                        publishedAt = LocalDateTime.now(),
                        source = Source(
                            id = "id",
                            name = "name"
                        )
                    )
                )
                add(
                    Article(
                        title = "title",
                        author = "author",
                        content = "content",
                        description = "description",
                        url = "url",
                        urlToImage = "urlToImage",
                        publishedAt = LocalDateTime.now(),
                        source = Source(
                            id = "id",
                            name = "name"
                        )
                    )
                )
                add(
                    Article(
                        title = "title",
                        author = "author",
                        content = "content",
                        description = "description",
                        url = "url",
                        urlToImage = "urlToImage",
                        publishedAt = LocalDateTime.now(),
                        source = Source(
                            id = "id",
                            name = "name"
                        )
                    )
                )
                add(
                    Article(
                        title = "title",
                        author = "author",
                        content = "content",
                        description = "description",
                        url = "url",
                        urlToImage = "urlToImage",
                        publishedAt = LocalDateTime.now(),
                        source = Source(
                            id = "id",
                            name = "name"
                        )
                    )
                )
                add(
                    Article(
                        title = "title",
                        author = "author",
                        content = "content",
                        description = "description",
                        url = "url",
                        urlToImage = "urlToImage",
                        publishedAt = LocalDateTime.now(),
                        source = Source(
                            id = "id",
                            name = "name"
                        )
                    )
                )
            })
            delay(1000)
            hideSplashScreen()
        }
    }

    fun hideSplashScreen() {
        viewModelScope.launch {
            _showSplashScreen.emit(false)
        }
    }
}