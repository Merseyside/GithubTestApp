package com.example.githubtestapp.presentation.di.components

import com.example.githubtestapp.presentation.di.modules.DetailFragmentModule
import com.example.githubtestapp.presentation.view.fragment.detailFragment.view.DetailFragment
import com.upstream.basemvvmimpl.presentation.di.qualifiers.FragmentScope
import dagger.Component

@FragmentScope
@Component(dependencies = [AppComponent::class], modules = [DetailFragmentModule::class])
interface DetailFragmentComponent {

    fun inject(fragment: DetailFragment)
}