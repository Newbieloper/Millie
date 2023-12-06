package com.newbieloper.millie.ui.main.model

import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

data class Article(
    val title: String,
    val author: String?,
    val content: String?,
    val description: String?,
    val url: String,
    val urlToImage: String?,
    val publishedAt: ZonedDateTime,
    val source: Source
) {
    fun publishedAtString(): String {
        return publishedAt.format(DateTimeFormatter.ofPattern("yyyy.MM.dd a h:mm"))
    }
}

data class Source(
    val id: String?,
    val name: String
)

fun com.newbieloper.millie.data.entity.Article.toArticle(): Article {
    return Article(
        title = title,
        author = author,
        content = content,
        description = description,
        url = url,
        urlToImage = urlToImage,
        publishedAt = publishedAt,
        source = Source(
            id = source.id,
            name = source.name
        )
    )
}