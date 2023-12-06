package com.newbieloper.millie.core.base

import android.content.Context
import android.util.Log
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.Priority
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.load.engine.bitmap_recycle.LruBitmapPool
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory
import com.bumptech.glide.load.engine.cache.LruResourceCache
import com.bumptech.glide.load.engine.cache.MemorySizeCalculator
import com.bumptech.glide.module.AppGlideModule
import com.bumptech.glide.request.RequestOptions

/**
 *  Glide 기본 설정에 관한 모듈 클래스
 *
 *  공통 설정 이후 빌드시 Glide 형태로 접근 가능
 */
@GlideModule
class BaseGlide : AppGlideModule() {

    override fun applyOptions(context: Context, builder: GlideBuilder) {
        val calculator = MemorySizeCalculator.Builder(context).build()

        val bitmapPoolSize = calculator.bitmapPoolSize
        val memoryCacheSize = calculator.memoryCacheSize
        val diskCacheSize = 1024 * 1024 * 50

        val requestOptions = RequestOptions()
            .priority(Priority.NORMAL)
            .format(DecodeFormat.PREFER_RGB_565)
            .timeout(1000 * 10)

        builder.setBitmapPool(LruBitmapPool(bitmapPoolSize.toLong()))
            .setMemoryCache(LruResourceCache(memoryCacheSize.toLong()))
            .setDiskCache(InternalCacheDiskCacheFactory(context, "image_disk_cache", diskCacheSize.toLong()))
            .setDefaultRequestOptions(requestOptions)
            .setLogLevel(Log.ERROR)
    }

    override fun isManifestParsingEnabled(): Boolean {
        return false
    }
}
