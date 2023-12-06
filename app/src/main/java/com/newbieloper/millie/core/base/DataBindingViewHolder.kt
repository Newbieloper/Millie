package com.newbieloper.millie.core.base

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class DataBindingViewHolder<BINDING : ViewDataBinding, DATA : Any> constructor(
    val binding: BINDING
) : RecyclerView.ViewHolder(binding.root) {

    abstract fun bind(data: DATA)
}