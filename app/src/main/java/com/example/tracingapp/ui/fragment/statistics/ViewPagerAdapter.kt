package com.example.tracingapp.ui.fragment.statistics

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(f: Fragment): FragmentStateAdapter(f) {


    override fun getItemCount(): Int {
        return 2 // GlobalFragment and MalaysiaFragment
    }

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> MalaysiaFragment()
            else -> GlobalFragment()
        }
    }
}