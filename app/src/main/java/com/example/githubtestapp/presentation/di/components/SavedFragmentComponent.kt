package com.example.githubtestapp.presentation.di.components

import com.example.githubtestapp.presentation.di.modules.SavedFragmentModule
import com.example.githubtestapp.presentation.view.fragment.savedFragment.view.SavedFragment
import com.upstream.basemvvmimpl.presentation.di.qualifiers.FragmentScope
import dagger.Component

@FragmentScope
@Component(dependencies = [AppComponent::class], modules = [SavedFragmentModule::class])
interface SavedFragmentComponent {

    fun inject(fragment: SavedFragment)
}