package com.newbieloper.millie.core.extension

import android.app.Application
import android.graphics.Point
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import javax.annotation.Nonnull

fun RequestManager.cacheImages(@Nonnull imageUrls: List<String>, size: Point? = null) {
    for (url in imageUrls) {
        if (size == null) {
            downloadOnly().load(url).submit()
        } else {
            downloadOnly().load(url).submit(size.x, size.y)
        }
    }
}

fun Application.clearMemoryCache() {
    Glide.get(this).clearMemory()
}