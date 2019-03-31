package com.example.githubtestapp.presentation.di.components

import com.example.githubtestapp.presentation.di.modules.SearchFragmentModule
import com.example.githubtestapp.presentation.view.fragment.searchFragment.view.SearchFragment
import com.upstream.basemvvmimpl.presentation.di.qualifiers.FragmentScope
import dagger.Component

@FragmentScope
@Component(dependencies = [AppComponent::class], modules = [SearchFragmentModule::class])
interface SearchFragmentComponent {

    fun inject(fragment: SearchFragment)
}