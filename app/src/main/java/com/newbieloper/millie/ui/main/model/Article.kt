package com.newbieloper.millie.ui.main.model

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

data class Article(
    val title: String,
    val author: String,
    val content: String,
    val description: String,
    val url: String,
    val urlToImage: String,
    val publishedAt: LocalDateTime,
    val source: Source
) {
    fun publishedAtString(): String {
        return publishedAt.format(DateTimeFormatter.ofPattern("yyyy.MM.dd a h:mm"))
    }
}

data class Source(
    val id: String,
    val name: String
)