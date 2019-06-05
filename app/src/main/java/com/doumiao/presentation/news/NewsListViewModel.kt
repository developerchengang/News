package com.doumiao.presentation.news

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.doumiao.domain.common.Mapper
import com.doumiao.domain.entities.NewsSourcesEntity
import com.doumiao.domain.usecases.NewsUseCase
import com.doumiao.presentation.common.BaseViewModel
import com.doumiao.presentation.entities.Data
import com.doumiao.presentation.entities.Error
import com.doumiao.presentation.entities.NewsSources
import com.doumiao.presentation.entities.Status

class NewsListViewModel(private val newsUseCase: NewsUseCase,
                        private val mapper: Mapper<NewsSourcesEntity, NewsSources>): BaseViewModel() {

    companion object {
        private const val TAG = "NewsViewModel"
    }



    private var mNews = MutableLiveData<Data<NewsSources>>()

    fun fetchNews() {
        val disposable = newsUseCase.getNews()
            .flatMap { mapper.Flowable(it) }
            .subscribe({ response ->
                Log.d(TAG, "On Next Called")
                mNews.value = Data(
                    responseType = Status.SUCCESSFUL,
                    data = response)
            }, {
                error ->
                Log.d(TAG, "On Error Called" + error.message)
                mNews.value = Data(
                    responseType = Status.ERROR,
                    error = Error(error.message))
            }, {
                Log.d(TAG, "On Complete Called")
            })

        addDisposable(disposable)
    }

    fun getNewsLiveData() = mNews

}