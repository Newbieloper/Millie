package com.newbieloper.millie.ui.main.adapter

import android.view.ViewGroup
import com.newbieloper.millie.core.base.DataBindingViewHolder
import com.newbieloper.millie.ui.main.model.Article
import com.newbieloper.millie.R
import com.newbieloper.millie.core.extension.binding
import com.newbieloper.millie.core.extension.setOnSafeClickListener
import com.newbieloper.millie.databinding.ViewHolderArticleBinding

class ArticleViewHolder(
    private val parent: ViewGroup,
    private val onItemClickListener: OnItemClickListener,
) : DataBindingViewHolder<ViewHolderArticleBinding, Article>(parent.binding(layoutResId)) {

    init {
        binding.apply {
            cvItem.setOnSafeClickListener {
                article?.let {
                    onItemClickListener.onClick(it)
                }
            }
        }
    }

    override fun bind(data: Article) {
        binding.apply {
            article = data
        }.executePendingBindings()
    }

    companion object {
        val layoutResId = R.layout.view_holder_article
    }

    interface OnItemClickListener {
        fun onClick(article: Article)
    }
}