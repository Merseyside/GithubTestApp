package com.example.githubtestapp.presentation.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.githubtestapp.data.db.RepoDB
import com.example.githubtestapp.data.entity.mapper.RepositoryDataMapper
import com.example.githubtestapp.presentation.view.fragment.savedFragment.model.SavedFragmentViewModel
import com.upstream.basemvvmimpl.presentation.fragment.BaseFragment
import dagger.Module
import dagger.Provides
import ru.terrakok.cicerone.Router

@Module
class SavedFragmentModule(private val fragment : BaseFragment) {

    @Provides
    internal fun savedFragmentViewModelProvider(
        router: Router,
        repoDb: RepoDB,
        repositoryDataMapper: RepositoryDataMapper
    ): ViewModelProvider.Factory {
        return SavedFragmentViewModelProviderFactory(router, repoDb, repositoryDataMapper)
    }

    @Provides
    internal fun provideSavedFragmentViewModel(factory: ViewModelProvider.Factory): SavedFragmentViewModel {
        return ViewModelProviders.of(fragment, factory).get(SavedFragmentViewModel::class.java)
    }

    class SavedFragmentViewModelProviderFactory(
        private val router : Router,
        private val repoDB: RepoDB,
        private val repositoryDataMapper: RepositoryDataMapper
    ) : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass == SavedFragmentViewModel::class.java) {
                return SavedFragmentViewModel(router, repoDB, repositoryDataMapper) as T
            }
            throw IllegalArgumentException("Unknown class title")
        }
    }
}