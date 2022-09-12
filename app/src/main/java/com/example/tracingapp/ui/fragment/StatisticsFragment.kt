package com.example.tracingapp.ui.fragment

import android.os.Bundle
import android.text.Html
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.viewpager2.widget.ViewPager2
import com.example.tracingapp.R
import com.example.tracingapp.ui.fragment.statistics.ViewPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class StatisticsFragment : Fragment() {
    var global: LinearLayout? = null
    var malaysia: LinearLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        activity?.setTitle(Html.fromHtml("<big><big>Statistics</big></big>"))

        val view: View = inflater.inflate(R.layout.fragment_statistics, container, false)
        val viewPager: ViewPager2 = view.findViewById(R.id.viewpager)
        val viewPagerAdapter = ViewPagerAdapter(this)
        viewPager.adapter = viewPagerAdapter

        val tabTitle = listOf("Malaysia", "Global")
        val tabLayout = view.findViewById<TabLayout>(R.id.tablayout)
        TabLayoutMediator(tabLayout, viewPager) {
            tabLayout, position -> tabLayout.text = tabTitle[position]
        }.attach()

        return view
    }
}