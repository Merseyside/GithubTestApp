package com.example.githubtestapp.presentation.base

import android.content.Context
import androidx.databinding.ViewDataBinding
import com.example.githubtestapp.GithubApplication
import com.example.githubtestapp.presentation.di.components.AppComponent
import com.upstream.basemvvmimpl.presentation.fragment.BaseMvvmFragment

abstract class BaseGithubFragment<B : ViewDataBinding, M : BaseGithubViewModel>
    : BaseMvvmFragment<B, M>() {

    protected fun getAppComponent(): AppComponent = GithubApplication.getInstance().appComponent

    override fun clear() {}

    override fun getApplicationContext(): Context {
        return GithubApplication.getInstance()
    }

    override fun getTitle(context: Context): String? {
        return ""
    }
}