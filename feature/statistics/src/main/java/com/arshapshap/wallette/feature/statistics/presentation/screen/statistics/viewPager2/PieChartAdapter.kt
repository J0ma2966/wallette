package com.arshapshap.wallette.feature.statistics.presentation.screen.statistics.viewPager2

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.arshapshap.wallette.feature.statistics.presentation.screen.statistics.StatisticsFragment

class PieChartAdapter(
    fragment: StatisticsFragment,
    private val dataSet: Array<Int>
) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int
            = dataSet.size

    override fun createFragment(position: Int): Fragment
            = PieChartFragment()
}