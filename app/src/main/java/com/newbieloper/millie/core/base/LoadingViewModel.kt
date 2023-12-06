package com.newbieloper.millie.core.base

import androidx.annotation.FloatRange
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
open class LoadingViewModel @Inject constructor() : ViewModel() {

    private val _loading = MutableStateFlow(Loading.End)
    val loading = _loading.asStateFlow()

    suspend fun showLoading(progress: Float? = null) {
        _loading.emit(progress?.let { Loading(progress) } ?: Loading.Start)
    }

    suspend fun hideLoading() {
        _loading.emit(Loading.End)
    }
}

data class Loading(
    @FloatRange(from = 0.0, to = 1.0) val progress: Float? = null,
) {
    companion object {
        val Start = Loading(0f)
        val End = Loading(1f)
    }
}