package com.doumiao.data.repository

import com.doumiao.data.api.RemoteNewsApi
import com.doumiao.data.entities.NewsDataEntityMapper
import com.doumiao.domain.entities.NewsSourcesEntity
import io.reactivex.Flowable

class NewsRemoteImpl constructor(private val api: RemoteNewsApi): NewsDataStore {

    private val newsMapper = NewsDataEntityMapper()

    override fun getNews(): Flowable<NewsSourcesEntity> {
        return api.getNews().map { newsMapper.mapToEntity(it) }
    }

}