package com.doumiao.presentation.mappers

import com.doumiao.domain.common.Mapper
import com.doumiao.domain.entities.NewsPublisherEntity
import com.doumiao.domain.entities.NewsSourcesEntity
import com.doumiao.presentation.entities.NewsPublisher
import com.doumiao.presentation.entities.NewsSources

class NewsEntityMapper: Mapper<NewsSourcesEntity, NewsSources>() {

    override fun mapFrom(from: NewsSourcesEntity): NewsSources {
        return NewsSources(
            status = from.status,
            articles = mapListArticlesToPresetation(from.articles)
        )
    }


    private fun mapListArticlesToPresetation(articles: List<NewsPublisherEntity>?): List<NewsPublisher> =
        articles?.map { mapListArticlesToPresetation(it) }
            ?: emptyList()

    private fun mapListArticlesToPresetation(response: NewsPublisherEntity): NewsPublisher = NewsPublisher(
        id = response.id,
        title = response.author,
        description = response.description,
        url = response.url,
        category = response.category
    )
}