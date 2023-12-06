package com.newbieloper.millie.di

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object GlideModule {

    @Provides
    @Singleton
    fun provideGlideDiskCacheRequestManager(
        @ApplicationContext context: Context
    ): RequestManager = Glide.with(context).applyDefaultRequestOptions(
        RequestOptions()
            .format(DecodeFormat.PREFER_RGB_565)
            .timeout(1000 * 10)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
    )
}