package com.dicoding.discovernusantara.ui.adapter

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.dicoding.discovernusantara.ui.HistorySitesFragment
import com.dicoding.discovernusantara.ui.ScanFragment

class SectionPagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = HistorySitesFragment()
            1 -> fragment = ScanFragment()
        }
        return fragment as Fragment
    }

}