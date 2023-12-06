package com.newbieloper.millie.ui.web

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.newbieloper.millie.core.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WebViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    val url = savedStateHandle.getStateFlow<String?>(Constants.IntentExtra.WEB_URL, null)
}