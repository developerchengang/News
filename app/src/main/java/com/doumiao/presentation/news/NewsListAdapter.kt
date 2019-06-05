package com.doumiao.presentation.news

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.doumiao.presentation.R
import com.doumiao.presentation.databinding.ItemNewsBinding
import com.doumiao.presentation.entities.NewsPublisher
import java.util.*

class NewsListAdapter: RecyclerView.Adapter<NewsListAdapter.ViewHolder>() {

    private lateinit var newsList: List<NewsPublisher>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemNewsBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), R.layout.item_news,
            parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return if(::newsList.isInitialized) newsList.size else 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.newsList = newsList[position]
        holder.binding.executePendingBindings()
}

    fun updateNewsList(newsPublisherList :List<NewsPublisher>){
        if (!::newsList.isInitialized)  {
            newsList = newsPublisherList
            notifyItemRangeInserted(0, newsList.size)
        } else {
            val result: DiffUtil.DiffResult = DiffUtil.calculateDiff(object : DiffUtil.Callback() {
                override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                    return newsList[oldItemPosition].id ==
                            newsPublisherList[newItemPosition].id
                }

                override fun getOldListSize(): Int {
                    return newsList.size
                }

                override fun getNewListSize(): Int {
                    return newsPublisherList.size
                }

                override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                    val newsPublisher = newsPublisherList[newItemPosition]
                    val old = newsPublisherList[oldItemPosition]
                    return newsPublisher.id == old.id
                            && Objects.equals(newsPublisher.url, old.url)
                }
            })

            newsList = newsPublisherList
            result.dispatchUpdatesTo(this)
        }
    }

    class ViewHolder(val binding: ItemNewsBinding) : RecyclerView.ViewHolder(binding.root)

}