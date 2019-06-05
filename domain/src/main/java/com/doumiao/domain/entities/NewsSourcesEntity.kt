package com.doumiao.domain.entities

class NewsSourcesEntity(
    val status: String? = null,
    val articles: List<NewsPublisherEntity> = emptyList()
)