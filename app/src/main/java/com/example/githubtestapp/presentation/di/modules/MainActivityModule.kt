package com.example.githubtestapp.presentation.di.modules

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.githubtestapp.presentation.view.activity.mainActivity.model.MainViewModel
import dagger.Module
import dagger.Provides
import ru.terrakok.cicerone.Router

@Module
class MainActivityModule(private val activity : AppCompatActivity) {

    @Provides
    internal fun mainViewModelProvider(router : Router): ViewModelProvider.Factory {
        return MainViewModelProviderFactory(router)
    }

    @Provides
    internal fun provideMainViewModel(factory: ViewModelProvider.Factory): MainViewModel {
        return ViewModelProviders.of(activity, factory).get(MainViewModel::class.java)
    }

    class MainViewModelProviderFactory(
        private val router : Router) : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass == MainViewModel::class.java) {
                return MainViewModel(router) as T
            }
            throw IllegalArgumentException("Unknown class title")
        }
    }
}