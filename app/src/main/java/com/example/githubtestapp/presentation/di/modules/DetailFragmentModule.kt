package com.example.githubtestapp.presentation.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.githubtestapp.domain.interactor.GetRepoDetailsInteractor
import com.example.githubtestapp.presentation.view.fragment.detailFragment.model.DetailFragmentViewModel
import com.upstream.basemvvmimpl.presentation.fragment.BaseFragment
import dagger.Module
import dagger.Provides
import ru.terrakok.cicerone.Router

@Module
class DetailFragmentModule(private val fragment: BaseFragment) {

    @Provides
    internal fun detailFragmentViewModelProvider(
        router: Router,
        getRepoDetailsUseCase: GetRepoDetailsInteractor
    ): ViewModelProvider.Factory {
        return DetailFragmentViewModelProviderFactory(router, getRepoDetailsUseCase)
    }

    @Provides
    internal fun provideDetailFragmentViewModel(factory: ViewModelProvider.Factory): DetailFragmentViewModel {
        return ViewModelProviders.of(fragment, factory).get(DetailFragmentViewModel::class.java)
    }

    class DetailFragmentViewModelProviderFactory(
        private val router : Router,
        private val getRepoDetailsUseCase: GetRepoDetailsInteractor
    ) : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass == DetailFragmentViewModel::class.java) {
                return DetailFragmentViewModel(router, getRepoDetailsUseCase) as T
            }
            throw IllegalArgumentException("Unknown class title")
        }
    }
}