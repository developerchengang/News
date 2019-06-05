package com.doumiao.data.entities

import com.doumiao.domain.entities.NewsPublisherEntity
import com.doumiao.domain.entities.NewsSourcesEntity

data class NewsSourcesData(val status: String? = null,
                           val articles: List<NewsPublisherData>)

class NewsDataEntityMapper {

    fun mapToEntity(data: NewsSourcesData?): NewsSourcesEntity? = NewsSourcesEntity(
        status = data?.status,
        articles = mapListArticlesToEntity(data?.articles)
    )

    private fun mapListArticlesToEntity(articles: List<NewsPublisherData>?)
            : List<NewsPublisherEntity> = articles?.map { mapArticleToEntity(it) } ?: emptyList()


    private fun mapArticleToEntity(response: NewsPublisherData): NewsPublisherEntity = NewsPublisherEntity(
        id = response.id,
        author = response.author,
        description = response.description,
        url = response.url,
        category = response.category
    )
}