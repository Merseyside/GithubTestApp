package com.example.githubtestapp.presentation.view.activity.mainActivity.model

import com.example.githubtestapp.presentation.base.BaseGithubViewModel
import com.example.githubtestapp.presentation.navigation.Screens
import ru.terrakok.cicerone.Router

class MainViewModel(private val router: Router) : BaseGithubViewModel() {

    override fun clearDisposables() {
    }

    override fun dispose() {
    }

    override fun onBackPressed(): Boolean {
        router.exit()

        return false
    }

    fun navigateToMainFragment() {
        router.newRootScreen(Screens.MainFragmentScreen())
    }
}