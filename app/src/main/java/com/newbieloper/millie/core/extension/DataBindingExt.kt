package com.newbieloper.millie.core.extension

import android.app.Activity
import android.content.Context
import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import androidx.annotation.DrawableRes
import androidx.annotation.LayoutRes
import androidx.appcompat.content.res.AppCompatResources
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

/**
 * activity binding
 */
fun <T : ViewDataBinding> Activity.binding(
    @LayoutRes layoutResId: Int
): T = DataBindingUtil.setContentView(this, layoutResId)

/**
 * fragment binding
 */
fun <T : ViewDataBinding> Fragment.binding(
    @LayoutRes layoutResId: Int,
    inflater: LayoutInflater,
    viewGroup: ViewGroup?
): T = DataBindingUtil.inflate(inflater, layoutResId, viewGroup, false)

/**
 * viewGroup binding (viewHolder)
 */
fun <T : ViewDataBinding> ViewGroup.binding(
    @LayoutRes layoutResId: Int
): T = DataBindingUtil.inflate(LayoutInflater.from(context), layoutResId, this, false)

fun ViewDataBinding.getContext(): Context = root.context

fun ViewDataBinding.getResources(): Resources = getContext().resources

fun ViewDataBinding.getDrawable(@DrawableRes resId: Int): Drawable? = AppCompatResources.getDrawable(getContext(), resId)

fun ViewDataBinding.getColor(@ColorRes resId: Int): Int = getContext().getColor(resId)

fun ViewDataBinding.getDimension(@DimenRes resId: Int): Float = getResources().getDimension(resId)