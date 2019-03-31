package com.example.githubtestapp.presentation.view.fragment.searchFragment.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.githubtestapp.data.repository.datasource.RepositoryDataSource
import com.example.githubtestapp.data.repository.datasource.RepositoryDataSourceFactory
import com.example.githubtestapp.data.repository.datasource.State
import com.example.githubtestapp.domain.RepositoryModel
import com.example.githubtestapp.presentation.base.BaseGithubViewModel
import com.example.githubtestapp.presentation.navigation.Screens
import com.example.githubtestapp.presentation.view.fragment.savedFragment.model.RepoViewModel
import ru.terrakok.cicerone.Router

class SearchFragmentViewModel(
    private val router: Router,
    private val repositoryDataSourceFactory: RepositoryDataSourceFactory
) : BaseGithubViewModel(), RepoViewModel {

    private val TAG = javaClass.simpleName

    lateinit var repoLiveData: LiveData<PagedList<RepositoryModel>>

    override fun clearDisposables() {
        repositoryDataSourceFactory.getCompositeDisposables().clear()
    }

    override fun dispose() {
        repositoryDataSourceFactory.getCompositeDisposables().dispose()
    }

    private fun initPaging() {

        val pagedListConfig = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(100)
            .setPageSize(100)
            .setPrefetchDistance(10)
            .build()

        repoLiveData = LivePagedListBuilder<Int, RepositoryModel>(repositoryDataSourceFactory, pagedListConfig)
            .build()
    }

    override fun loadRepositories() {
        initPaging()
    }

    fun getStateLiveData(): LiveData<State> = Transformations.switchMap<RepositoryDataSource,
            State>(repositoryDataSourceFactory.propertyDataSourceLiveData, RepositoryDataSource::state)


    override fun navigateToDetailsScreen() {
        router.navigateTo(Screens.DetailFragmentScreen())
    }

    fun refresh() {
        repositoryDataSourceFactory.propertyDataSourceLiveData.value?.invalidate()
    }

    fun retry() {
        repositoryDataSourceFactory.propertyDataSourceLiveData.value?.retry()
    }

    fun saveRepository(repo: RepositoryModel) {

    }
}