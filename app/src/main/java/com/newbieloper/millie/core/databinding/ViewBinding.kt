package com.newbieloper.millie.core.databinding

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.ColorInt
import androidx.core.view.updateLayoutParams
import androidx.databinding.BindingAdapter
import com.newbieloper.millie.core.extension.gone
import com.newbieloper.millie.core.extension.invisible
import com.newbieloper.millie.core.extension.setOnSafeClickListener
import com.newbieloper.millie.core.extension.visible
import com.newbieloper.millie.core.extension.visibleOrGone
import com.newbieloper.millie.core.extension.visibleOrInvisible

object ViewBinding {

    @JvmStatic
    @BindingAdapter("visible")
    fun bindVisible(view: View, visible: Boolean) = view.takeIf { visible }?.visible()

    @JvmStatic
    @BindingAdapter("gone")
    fun bindGone(view: View, gone: Boolean) = view.takeIf { gone }?.gone()

    @JvmStatic
    @BindingAdapter("invisible")
    fun bindInvisible(view: View, invisible: Boolean) = view.takeIf { invisible }?.invisible()

    @JvmStatic
    @BindingAdapter("visibleOrGone")
    fun bindVisibleOrGone(view: View, visible: Boolean) = view.visibleOrGone(visible)

    @JvmStatic
    @BindingAdapter("visibleOrInvisible")
    fun bindVisibleOrInvisible(view: View, visible: Boolean) = view.visibleOrInvisible(visible)

    @JvmStatic
    @BindingAdapter("onSafeClick")
    fun bindOnSafeClick(view: View, onClickListener: View.OnClickListener) = view.setOnSafeClickListener(onClickListener)

    @JvmStatic
    @BindingAdapter(value = ["layout_marginStart", "layout_marginEnd", "layout_marginTop", "layout_marginBottom"], requireAll = false)
    fun bindSetLayoutMargin(view: View, marginStart: Float = 0.toFloat(), marginEnd: Float = 0.toFloat(), marginTop: Float = 0.toFloat(), marginBottom: Float = 0.toFloat()) {
        view.updateLayoutParams<ViewGroup.MarginLayoutParams> {
            this.marginStart = marginStart.toInt()
            this.marginEnd = marginEnd.toInt()
            this.topMargin = marginTop.toInt()
            this.bottomMargin = marginBottom.toInt()
        }
    }

    @JvmStatic
    @BindingAdapter("tint")
    fun bindTint(view: ImageView, @ColorInt color: Int) = view.setColorFilter(color)
}
