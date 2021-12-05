package com.example.buah.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.buah.R
import com.example.buah.fragment.MainFragment

class PenghubungAdapter(
    private val mContext: Context,
    fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager,
    BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    var username: String? = null

    private val TAB_TITLE = intArrayOf(R.string.followers, R.string.following)

    override fun getItem(position: Int): Fragment {
        return MainFragment.newInstance(position + 1)
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return mContext.resources.getString(TAB_TITLE[position])
    }

    override fun getCount(): Int {
        return 2
    }
}

