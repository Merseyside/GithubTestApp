package com.example.githubtestapp.presentation.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.githubtestapp.data.repository.datasource.RepositoryDataSourceFactory
import com.example.githubtestapp.domain.interactor.SaveRepoInteractor
import com.example.githubtestapp.presentation.view.fragment.searchFragment.model.SearchFragmentViewModel
import com.upstream.basemvvmimpl.presentation.fragment.BaseFragment
import dagger.Module
import dagger.Provides
import ru.terrakok.cicerone.Router


@Module
class SearchFragmentModule(private val fragment : BaseFragment) {

    @Provides
    internal fun searchFragmentViewModelProvider(
        router: Router,
        repositoryDataSource: RepositoryDataSourceFactory,
        saveRepoUseCase: SaveRepoInteractor): ViewModelProvider.Factory {
        return SearchFragmentViewModelProviderFactory(router, repositoryDataSource, saveRepoUseCase)
    }

    @Provides
    internal fun provideSearchFragmentViewModel(factory: ViewModelProvider.Factory): SearchFragmentViewModel {
        return ViewModelProviders.of(fragment, factory).get(SearchFragmentViewModel::class.java)
    }

    class SearchFragmentViewModelProviderFactory(
        private val router : Router,
        private val repositoryDataSource: RepositoryDataSourceFactory,
        private val saveRepoUseCase: SaveRepoInteractor
    ) : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass == SearchFragmentViewModel::class.java) {
                return SearchFragmentViewModel(router, repositoryDataSource, saveRepoUseCase) as T
            }
            throw IllegalArgumentException("Unknown class title")
        }
    }
}