package com.newbieloper.millie.data.local.article

import androidx.room.*
import com.newbieloper.millie.data.entity.ArticleEntity

@Dao
interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllArticleList(articleList: List<ArticleEntity>)

    @Update
    fun updateArticle(article: ArticleEntity)

    @Transaction
    @Query("SELECT * FROM Article")
    fun getArticleList(): List<ArticleEntity>
}