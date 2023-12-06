package com.newbieloper.millie.ui.main

import androidx.lifecycle.viewModelScope
import com.newbieloper.millie.core.base.LoadingViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : LoadingViewModel() {

    private val _showSplashScreen = MutableStateFlow(true)
    val showSplashScreen = _showSplashScreen.asStateFlow()

    init {
        viewModelScope.launch {
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