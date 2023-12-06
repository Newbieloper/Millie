package com.newbieloper.millie.ui.web

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.newbieloper.millie.R
import com.newbieloper.millie.core.Constants
import com.newbieloper.millie.core.base.DataBindingActivity
import com.newbieloper.millie.core.extension.gone
import com.newbieloper.millie.core.extension.visible
import com.newbieloper.millie.databinding.ActivityWebBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class WebActivity : DataBindingActivity<ActivityWebBinding>(R.layout.activity_web) {

    private val viewModel by viewModels<WebViewModel>()

    override fun ActivityWebBinding.applyBinding() {
        lifecycleOwner = this@WebActivity
        activity = this@WebActivity
        viewModel = this@WebActivity.viewModel
    }

    override fun setup() {
        setupArticleWebView()
        observeArticleUrl()
    }

    override fun onResume() {
        super.onResume()
        binding.wvArticle.onResume()
    }

    override fun onPause() {
        super.onPause()
        binding.wvArticle.onPause()
    }

    override fun onDestroy() {
        binding.wvArticle.destroy()
        super.onDestroy()
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun setupArticleWebView() {
        binding.wvArticle.apply {
            settings.javaScriptEnabled = true

            webViewClient = object : WebViewClient() {
                override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                    super.onPageStarted(view, url, favicon)
                    binding.pbLoading.visible()
                }

                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)
                    binding.pbLoading.gone()
                }
            }

            webChromeClient = object : WebChromeClient() {
                override fun onProgressChanged(view: WebView?, newProgress: Int) {
                    super.onProgressChanged(view, newProgress)
                    binding.pbLoading.progress = newProgress
                }
            }
        }
    }

    private fun observeArticleUrl() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.url.collectLatest {
                    it?.let { url ->
                        binding.wvArticle.loadUrl(url)
                    }
                }
            }
        }
    }

    companion object {
        fun getIntent(
            context: Context,
            url: String? = null
        ): Intent = Intent(context, WebActivity::class.java).apply {
            putExtra(Constants.IntentExtra.WEB_URL, url)
        }
    }
}