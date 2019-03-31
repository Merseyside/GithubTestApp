package com.example.githubtestapp.presentation.di.components

import com.example.githubtestapp.presentation.di.modules.MainActivityModule
import com.example.githubtestapp.presentation.view.activity.mainActivity.view.MainActivity
import com.upstream.basemvvmimpl.presentation.di.qualifiers.ActivityScope
import dagger.Component

@ActivityScope
@Component(dependencies = [AppComponent::class], modules = [MainActivityModule::class])
interface MainActivityComponent {

    fun inject(activity: MainActivity)
}