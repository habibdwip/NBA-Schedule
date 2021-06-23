package com.habib.nba

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.habib.nba.view.FavoriteFragment
import com.habib.nba.view.ListTeamFragment
import com.habib.nba.view.MatchFragment

class PagerAdapter(fm: FragmentManager) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getCount(): Int = 3

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> MatchFragment()
            1 -> ListTeamFragment()
            2 -> FavoriteFragment()
            else -> throw IllegalStateException("Fragment Not Found")
        }
    }

    override fun getPageTitle(position: Int): CharSequence {
        return when (position) {
            0 -> "Sport"
            1 -> "Team"
            2 -> "Favourite"
            else -> throw IllegalStateException("Fragment Not Found")
        }
    }
}