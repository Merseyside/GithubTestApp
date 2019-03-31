package com.example.githubtestapp.presentation.navigation

import androidx.fragment.app.Fragment
import com.example.githubtestapp.presentation.view.fragment.detailFragment.view.DetailFragment
import com.example.githubtestapp.presentation.view.fragment.mainFragment.view.MainFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

class Screens {

    class DetailFragmentScreen : SupportAppScreen() {
        override fun getFragment(): Fragment {
            return DetailFragment.newInstance()
        }
    }

    class MainFragmentScreen : SupportAppScreen() {

        override fun getFragment(): Fragment {
            return MainFragment.newInstance()
        }
    }
}