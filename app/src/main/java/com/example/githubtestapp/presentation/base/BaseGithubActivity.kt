package com.example.githubtestapp.presentation.base

import androidx.databinding.ViewDataBinding
import com.example.githubtestapp.GithubApplication
import com.example.githubtestapp.presentation.di.components.AppComponent
import com.upstream.basemvvmimpl.presentation.activity.BaseMvvmActivity

abstract class BaseGithubActivity<B : ViewDataBinding, M : BaseGithubViewModel> : BaseMvvmActivity<B, M>()  {

    protected fun getAppComponent(): AppComponent = GithubApplication.getInstance().appComponent

    override fun clear() {
    }
}