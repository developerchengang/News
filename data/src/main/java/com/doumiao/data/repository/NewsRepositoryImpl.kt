package com.doumiao.data.repository

import com.doumiao.domain.entities.NewsSourcesEntity
import com.doumiao.domain.repositories.NewsRepository
import io.reactivex.Flowable

class NewsRepositoryImpl(private val remote: NewsRemoteImpl): NewsRepository {

    override fun getNews(): Flowable<NewsSourcesEntity> {
        return remote.getNews()
    }

}