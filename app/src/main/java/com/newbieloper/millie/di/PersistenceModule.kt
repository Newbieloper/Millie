package com.newbieloper.millie.di

import android.content.Context
import com.google.gson.Gson
import com.newbieloper.millie.data.local.LocalDatabase
import com.newbieloper.millie.data.local.article.ArticleDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object PersistenceModule {

    @Provides
    @Singleton
    fun provideLocalDatabase(@ApplicationContext context: Context, gson: Gson): LocalDatabase =
        LocalDatabase.create(context, gson)

    @Provides
    @Singleton
    fun provideArticleDao(localDatabase: LocalDatabase): ArticleDao =
        localDatabase.articleDao()
}