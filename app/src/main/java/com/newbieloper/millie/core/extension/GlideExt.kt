package com.newbieloper.millie.core.extension

import android.app.Application
import android.graphics.Point
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import javax.annotation.Nonnull

fun RequestManager.cacheImage(@Nonnull imageUrl: String, size: Point? = null) {
    if (size == null) {
        downloadOnly().load(imageUrl).submit()
    } else {
        downloadOnly().load(imageUrl).submit(size.x, size.y)
    }
}

fun RequestManager.cacheImages(@Nonnull imageUrls: List<String>, size: Point? = null) {
    for (url in imageUrls) {
        cacheImage(url, size)
    }
}

fun Application.clearMemoryCache() {
    Glide.get(this).clearMemory()
}