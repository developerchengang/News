package com.doumiao.presentation.news

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.doumiao.presentation.R
import com.doumiao.presentation.databinding.ActivityNewsBinding
import com.doumiao.presentation.entities.Status
import org.koin.android.viewmodel.ext.android.viewModel

class NewsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNewsBinding
    private val newsViewModel by viewModel<NewsListViewModel>()

    val loadingVisibility = MutableLiveData<Int>()

    val newsListAdapter: NewsListAdapter = NewsListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_news)
        binding.data = this
        binding.newsList.adapter = newsListAdapter
        newsViewModel.fetchNews()
    }

    override fun onStart() {
        super.onStart()
        newsViewModel.getNewsLiveData().observe(this, Observer {
            when(it?.responseType) {
                Status.LOADING ->
                    oStart()
                Status.SUCCESSFUL -> {
                    onFinish()
                    it.data?.apply {
                        newsListAdapter.updateNewsList(articles)
                    }
                }
                Status.ERROR -> {
                    onFinish()
                }
            }
        })
    }

    private fun oStart() {
        loadingVisibility.value = View.VISIBLE
    }

    private fun onFinish() {
        loadingVisibility.value = View.GONE
    }
}
