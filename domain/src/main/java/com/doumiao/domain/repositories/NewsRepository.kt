package com.doumiao.domain.repositories

import com.doumiao.domain.entities.NewsSourcesEntity
import io.reactivex.Flowable

interface NewsRepository {

    fun getNews(): Flowable<NewsSourcesEntity>

}