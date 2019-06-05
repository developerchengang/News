package com.doumiao.presentation.di

import com.doumiao.data.api.RemoteNewsApi
import com.doumiao.data.repository.NewsRemoteImpl
import com.doumiao.data.repository.NewsRepositoryImpl
import com.doumiao.domain.repositories.NewsRepository
import com.doumiao.domain.usecases.NewsUseCase
import com.doumiao.presentation.common.AsyncFlowableTransformer
import com.doumiao.presentation.mappers.NewsEntityMapper
import com.doumiao.presentation.news.NewsListViewModel
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import retrofit2.Retrofit

private const val BASE_URL = "https://newsapi.org/v2/"
private const val RETROFIT_INSTANCE = "Retrofit"
private const val API = "Api"
private const val NEWS_USE_CASE = "newsUseCase"

val mViewModel = module {
    viewModel {
        NewsListViewModel(newsUseCase = get(NEWS_USE_CASE), mapper = NewsEntityMapper())
    }
}

val mNetworkModules = module {
    single(name = RETROFIT_INSTANCE) { createNetworkClient(BASE_URL) }
    single(name = API) { (get(RETROFIT_INSTANCE) as Retrofit).create(RemoteNewsApi::class.java) }
}

val mUseCaseModules = module {
    factory(name = "newsUseCase") { NewsUseCase(transformer = AsyncFlowableTransformer(), repositories = get())}
}

var mRepositoryModules = module {
    single(name = "remote") { NewsRemoteImpl(api = get(API)) }
    single { NewsRepositoryImpl(remote = get("remote")) as NewsRepository }
}