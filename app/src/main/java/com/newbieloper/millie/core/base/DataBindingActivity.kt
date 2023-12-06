package com.newbieloper.millie.core.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ViewDataBinding
import com.newbieloper.millie.core.extension.binding

/**
 * DataBinding 를 기본으로 지원하는 Activity
 */
abstract class DataBindingActivity<BINDING : ViewDataBinding> constructor(
    @LayoutRes private val layoutResId: Int
) : AppCompatActivity() {

    private var _binding: BINDING? = null
    protected val binding: BINDING
        get() = checkNotNull(_binding) {
            "AppCompatActivity $this binding cannot be accessed before onCreate() or after onDestroy()"
        }

    protected abstract fun BINDING.applyBinding()

    protected abstract fun setup()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = binding<BINDING>(layoutResId).apply {
            applyBinding()
        }
        binding.executePendingBindings()
        setup()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding?.unbind()
        _binding = null
    }
}