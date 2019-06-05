package com.doumiao.data.repository

import com.doumiao.domain.entities.NewsSourcesEntity
import io.reactivex.Flowable

interface NewsDataStore {

    fun getNews(): Flowable<NewsSourcesEntity>

}