package com.example.tracingapp.ui.news

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tracingapp.R

class NewsAdapter(val news: Array<News>): RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    class NewsViewHolder(itemView: View):
        RecyclerView.ViewHolder(itemView) {
        val tv_title = itemView.findViewById<TextView>(R.id.tv_news_title)
        val tv_description = itemView.findViewById<TextView>(R.id.tv_news_description)
        val tv_date = itemView.findViewById<TextView>(R.id.tv_news_date)
        val iv_news_image = itemView.findViewById<ImageView>(R.id.iv_news_img)

        fun bind(news: News) {
            tv_title.text = news.title
            tv_description.text = news.description
            iv_news_image.setImageResource(news.image)
            tv_date.text = news.date.toString()
        }
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(news[position])
    }

    override fun getItemCount(): Int {
        return news.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.items_news, parent, false)
        return NewsViewHolder(view)
    }
}