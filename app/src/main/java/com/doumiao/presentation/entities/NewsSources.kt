package com.doumiao.presentation.entities

data class NewsSources(
    var status: String? = null,
    var articles: List<NewsPublisher> = emptyList()
)