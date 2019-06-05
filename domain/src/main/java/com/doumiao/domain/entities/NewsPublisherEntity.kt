package com.doumiao.domain.entities

data class NewsPublisherEntity(
    var id: Int =0,
    var author: String? = null,
    var description: String? = null,
    var url: String? = null,
    var category: String? = null
)