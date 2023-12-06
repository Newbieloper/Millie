package com.newbieloper.millie.ui.main

import android.os.Bundle
import androidx.activity.addCallback
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.newbieloper.millie.R
import com.newbieloper.millie.core.base.DataBindingActivity
import com.newbieloper.millie.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : DataBindingActivity<ActivityMainBinding>(R.layout.activity_main) {

    private val viewModel by viewModels<MainViewModel>()
    override fun ActivityMainBinding.applyBinding() {
        lifecycleOwner = this@MainActivity
        activity = this@MainActivity
        viewModel = this@MainActivity.viewModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen().apply {
            setKeepOnScreenCondition {
                viewModel.showSplashScreen.value
            }
        }
        super.onCreate(savedInstanceState)
    }

    override fun setup() {
        setupBackPressedDispatcher()
    }

    private fun setupBackPressedDispatcher() {
        onBackPressedDispatcher.addCallback {
            if (viewModel.showSplashScreen.value.not()) {
                finish()
            }
        }
    }
}