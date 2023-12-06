package com.newbieloper.millie.core.databinding

import android.graphics.drawable.Drawable
import android.net.Uri
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target

object ImageViewBinding {

    @JvmStatic
    @BindingAdapter(value = ["glide_url", "glide_placeholder"], requireAll = false)
    fun bindGlide(view: ImageView, url: String?, placeholder: Drawable?) {
        Glide.with(view)
            .load(url)
            .placeholder(placeholder)
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            .into(view)
    }

    @JvmStatic
    @BindingAdapter(value = ["glide_url", "glide_placeholder", "glide_centerCrop"], requireAll = false)
    fun bindGlide(view: ImageView, url: String?, placeholder: Drawable?, isCenterCrop: Boolean?) {
        placeholder?.let {
            view.scaleType = ImageView.ScaleType.CENTER_INSIDE
        }

        Glide.with(view)
            .load(url)
            .placeholder(placeholder)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>, isFirstResource: Boolean): Boolean {
                    placeholder?.let { view.scaleType = ImageView.ScaleType.CENTER_INSIDE }
                    return false
                }

                override fun onResourceReady(resource: Drawable, model: Any, target: Target<Drawable>?, dataSource: DataSource, isFirstResource: Boolean): Boolean {
                    if (isCenterCrop == true) view.scaleType = ImageView.ScaleType.CENTER_CROP
                    return false
                }

            })
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            .into(view)
    }

    @JvmStatic
    @BindingAdapter(value = ["glide_uri", "glide_placeholder", "glide_centerCrop"], requireAll = false)
    fun bindGlide(view: ImageView, uri: Uri?, placeholder: Drawable?, isCenterCrop: Boolean?) {
        placeholder?.let {
            view.scaleType = ImageView.ScaleType.CENTER_INSIDE
        }

        Glide.with(view)
            .load(uri)
            .placeholder(placeholder)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>, isFirstResource: Boolean): Boolean {
                    placeholder?.let { view.scaleType = ImageView.ScaleType.CENTER_INSIDE }
                    return false
                }

                override fun onResourceReady(resource: Drawable, model: Any, target: Target<Drawable>?, dataSource: DataSource, isFirstResource: Boolean): Boolean {
                    if (isCenterCrop == true) view.scaleType = ImageView.ScaleType.CENTER_CROP
                    return false
                }

            })
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            .into(view)
    }

    @JvmStatic
    @BindingAdapter("drawableId")
    fun bindDrawableId(view: ImageView, @DrawableRes id: Int) {
        view.setImageResource(id)
    }

}