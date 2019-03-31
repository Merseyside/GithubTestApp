package com.example.githubtestapp.presentation.view.fragment.mainFragment.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.ViewPager
import com.example.githubtestapp.GithubApplication
import com.example.githubtestapp.R
import com.example.githubtestapp.presentation.view.fragment.mainFragment.adapter.SectionsPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.upstream.basemvvmimpl.presentation.fragment.BaseFragment

class MainFragment : BaseFragment() {

    companion object {

        fun newInstance() : MainFragment {
            return MainFragment()
        }
    }

    override fun getApplicationContext(): Context {
        return GithubApplication.getInstance()
    }

    override fun getTitle(context: Context): String? {
        return ""
    }

    override fun updateLanguage(context: Context) {}

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        doLayout(view)
    }

    private fun doLayout(view: View) {
        val viewPager = view.findViewById<ViewPager>(R.id.view_pager)
        val tabs = view.findViewById<TabLayout>(R.id.tabs)

        val sectionsPagerAdapter =
            SectionsPagerAdapter(
                activity!!,
                this.childFragmentManager
            )

        viewPager.adapter = sectionsPagerAdapter

        tabs.setupWithViewPager(viewPager)
    }
}