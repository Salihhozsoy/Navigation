package com.example.navigationfirstproject.ui.news

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.navigationfirstproject.databinding.NewsListItemBinding
import com.example.navigationfirstproject.entity.News

class NewsAdapter(
    private val context: Context,
    private val news: List<News>,
    val onClick: (new: News) -> Unit,
    val onRemove:(new: News)->Unit
) : RecyclerView.Adapter<NewsAdapter.CustomViewHolder>() {

    class CustomViewHolder(binding: NewsListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val tvListTitle = binding.tvNewsTitle
        val ivNewsImage = binding.ivNewsImage
        val tvNewsContent = binding.tvNewsContent
        val tvNewsPostTime=binding.tvNewsPostTime
        val ivDelete=binding.ivDelete
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsAdapter.CustomViewHolder {
        val binding = NewsListItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return CustomViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsAdapter.CustomViewHolder, position: Int) {
        val news = news[position]

        holder.itemView.setOnClickListener {
            onClick(news)
        }
        holder.tvListTitle.text= news.title
        holder.tvNewsContent.text=news.description
        holder.tvNewsPostTime.text=news.postTime
        holder.ivNewsImage.load(news.imageUrl)

        holder.ivDelete.setOnClickListener {
            onRemove(news)
        }
    }

    override fun getItemCount(): Int {
        return news.size
    }
}