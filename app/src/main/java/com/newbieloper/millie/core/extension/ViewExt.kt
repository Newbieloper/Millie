package com.newbieloper.millie.core.extension

import android.view.View
import com.newbieloper.millie.core.base.OnSafeClickListener

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun View.visibleOrGone(visible: Boolean) {
    if (visible) visible() else gone()
}

fun View.visibleOrInvisible(visible: Boolean) {
    if (visible) visible() else invisible()
}

fun View.setOnSafeClickListener(onClickListener: View.OnClickListener) {
    setOnClickListener(OnSafeClickListener(onClickListener))
}