package com.example.tracingapp.ui.fragment

import android.os.Bundle
import android.text.Html
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tracingapp.ui.news.News
import com.example.tracingapp.ui.news.NewsAdapter
import com.example.tracingapp.R

class HomeFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        activity?.setTitle(Html.fromHtml("<big><big>MyCare</big></big>"))

        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        // Data
        val news: Array<News> =
            arrayOf(
                News(
                    id = 1,
                    image = R.drawable.img_news,
                    title = "Egestas, habitant dolor vulputate malesuada viverra.",
                    description = "Urna. Nec taciti dapibus porttitor quis elit nisi, blandit pharetra imperdiet. Vitae natoque sit posuere magna cras et curabitur ullamcorper erat. Dictumst suscipit. Elementum lacus mus potenti.",
                    date = "1 April 2022"
                ),
                News(
                    id = 2,
                    image = R.drawable.img_news,
                    title = "Egestas, habitant dolor vulputate malesuada viverra.",
                    description = "Urna. Nec taciti dapibus porttitor quis elit nisi, blandit pharetra imperdiet. Vitae natoque sit posuere magna cras et curabitur ullamcorper erat. Dictumst suscipit. Elementum lacus mus potenti.",
                    date = "1 April 2022"
                ),
                News(
                    id = 3,
                    image = R.drawable.img_news,
                    title = "Egestas, habitant dolor vulputate malesuada viverra.",
                    description = "Urna. Nec taciti dapibus porttitor quis elit nisi, blandit pharetra imperdiet. Vitae natoque sit posuere magna cras et curabitur ullamcorper erat. Dictumst suscipit. Elementum lacus mus potenti.",
                    date = "1 April 2022"
                ),
                News(
                    id = 4,
                    image = R.drawable.img_news,
                    title = "Egestas, habitant dolor vulputate malesuada viverra.",
                    description = "Urna. Nec taciti dapibus porttitor quis elit nisi, blandit pharetra imperdiet. Vitae natoque sit posuere magna cras et curabitur ullamcorper erat. Dictumst suscipit. Elementum lacus mus potenti.",
                    date = "1 April 2022"
                ),
                News(
                    id = 5,
                    image = R.drawable.img_news,
                    title = "Egestas, habitant dolor vulputate malesuada viverra.",
                    description = "Urna. Nec taciti dapibus porttitor quis elit nisi, blandit pharetra imperdiet. Vitae natoque sit posuere magna cras et curabitur ullamcorper erat. Dictumst suscipit. Elementum lacus mus potenti.",
                    date = "1 April 2022"
                ),
                News(
                    id = 6,
                    image = R.drawable.img_news,
                    title = "Egestas, habitant dolor vulputate malesuada viverra.",
                    description = "Urna. Nec taciti dapibus porttitor quis elit nisi, blandit pharetra imperdiet. Vitae natoque sit posuere magna cras et curabitur ullamcorper erat. Dictumst suscipit. Elementum lacus mus potenti.",
                    date = "1 April 2022"
                )
            )

        val recent_news: RecyclerView = view.findViewById(R.id.rv_news)
        recent_news.adapter = NewsAdapter(news)
        recent_news.setNestedScrollingEnabled(false)
    }
}