package com.example.githubtestapp.presentation.view.fragment.mainFragment.adapter

import android.content.Context
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.githubtestapp.R
import com.example.githubtestapp.presentation.view.fragment.savedFragment.view.SavedFragment
import com.example.githubtestapp.presentation.view.fragment.searchFragment.view.SearchFragment
import com.upstream.basemvvmimpl.presentation.fragment.BaseFragment

private val TAB_TITLES = arrayOf(
    R.string.tab_text_1,
    R.string.tab_text_2
)

class SectionsPagerAdapter(
    private val context: Context,
    fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

    override fun getItem(position: Int): BaseFragment {

        return when(position) {
            0 -> {
                SearchFragment.newInstance()
            }
            else -> {
                SavedFragment.newInstance()
            }
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return context.resources.getString(TAB_TITLES[position])
    }

    override fun getCount(): Int {
        // Show 2 total pages.
        return 2
    }
}