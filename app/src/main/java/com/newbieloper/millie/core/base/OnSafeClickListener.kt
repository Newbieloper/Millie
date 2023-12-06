package com.newbieloper.millie.core.base

import android.os.SystemClock
import android.view.View

class OnSafeClickListener(private val clickListener: View.OnClickListener?) : View.OnClickListener {

    private var lastClickedTime = 0L

    override fun onClick(v: View) {
        if (SystemClock.elapsedRealtime() - lastClickedTime < THRESHOLD_ELAPSED_TIME) return
        lastClickedTime = SystemClock.elapsedRealtime()
        clickListener?.onClick(v)
    }

    companion object {
        private const val THRESHOLD_ELAPSED_TIME = 1000L
    }
}