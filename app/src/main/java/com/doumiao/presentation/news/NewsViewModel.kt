package com.doumiao.presentation.news

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.doumiao.presentation.entities.NewsPublisher

class NewsViewModel: ViewModel() {

    private val title = MutableLiveData<String>()
    private val url = MutableLiveData<String>()

    public fun bind(newsPublisher: NewsPublisher) {
        title.value = newsPublisher.title
        url.value = newsPublisher.url
    }

    fun getTitle():MutableLiveData<String>{
        return title
    }

    fun getUrl():MutableLiveData<String>{
        return url
    }

}