package com.newbieloper.millie.ui.main

import android.os.Bundle
import android.util.TypedValue
import androidx.activity.addCallback
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.fondesa.recyclerviewdivider.dividerBuilder
import com.newbieloper.millie.R
import com.newbieloper.millie.core.base.DataBindingActivity
import com.newbieloper.millie.databinding.ActivityMainBinding
import com.newbieloper.millie.ui.main.adapter.ArticleListAdapter
import com.newbieloper.millie.ui.main.adapter.ArticleViewHolder
import com.newbieloper.millie.ui.main.model.Article
import com.newbieloper.millie.ui.web.WebActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : DataBindingActivity<ActivityMainBinding>(R.layout.activity_main) {

    private val viewModel by viewModels<MainViewModel>()

    private lateinit var articleListAdapter: ArticleListAdapter

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
        setupArticleList()
        observeArticleList()
    }

    private fun setupBackPressedDispatcher() {
        onBackPressedDispatcher.addCallback {
            if (viewModel.showSplashScreen.value.not()) {
                finish()
            }
        }
    }

    private fun setupArticleList() {
        binding.rvArticle.apply {
            articleListAdapter = ArticleListAdapter(
                object : ArticleViewHolder.OnItemClickListener {
                    override fun onClick(article: Article) {
                        viewModel.readArticle(article)
                        navigateToWeb(article.url)
                    }
                }
            )
            adapter = articleListAdapter
            context.dividerBuilder()
                .asSpace()
                .size(20, TypedValue.COMPLEX_UNIT_DIP)
                .showFirstDivider()
                .showLastDivider()
                .build()
                .addTo(this)
        }
    }

    private fun observeArticleList() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.articleList.collectLatest {
                    articleListAdapter.submitList(it)
                }
            }
        }
    }

    fun navigateToWeb(url: String) {
        WebActivity.getIntent(this@MainActivity, url).run {
            startActivity(this)
        }
    }
}