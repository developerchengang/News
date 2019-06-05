package com.doumiao.domain.usecases

import com.doumiao.domain.common.BaseFlowableUseCase
import com.doumiao.domain.common.FlowableRxTransformer
import com.doumiao.domain.entities.NewsSourcesEntity
import com.doumiao.domain.repositories.NewsRepository
import io.reactivex.Flowable


class NewsUseCase(private val transformer: FlowableRxTransformer<NewsSourcesEntity>,
                  private val repositories: NewsRepository): BaseFlowableUseCase<NewsSourcesEntity>(transformer) {

    override fun createFlowable(data: Map<String, Any>?): Flowable<NewsSourcesEntity> {
        return repositories.getNews()
    }

    fun getNews(): Flowable<NewsSourcesEntity> {
        val data = HashMap<String, String>()
        return single(data)
    }

}