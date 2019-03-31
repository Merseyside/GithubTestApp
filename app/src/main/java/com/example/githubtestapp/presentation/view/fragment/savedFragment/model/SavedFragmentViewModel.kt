package com.example.githubtestapp.presentation.view.fragment.savedFragment.model

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.githubtestapp.data.db.RepoDB
import com.example.githubtestapp.data.db.RepoEntity
import com.example.githubtestapp.data.entity.mapper.RepositoryDataMapper
import com.example.githubtestapp.domain.RepositoryModel
import com.example.githubtestapp.presentation.base.BaseGithubViewModel
import ru.terrakok.cicerone.Router

class SavedFragmentViewModel(private val router: Router,
                             private val repoDB: RepoDB,
                             private var repoDataMapper: RepositoryDataMapper
) : BaseGithubViewModel(), RepoViewModel {

    lateinit var propertyLiveData: LiveData<PagedList<RepositoryModel>>

    override fun clearDisposables() {
    }

    override fun dispose() {
    }

    private fun initPaging() {

        val pagedListConfig = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(15)
            .setPageSize(10)
            .setPrefetchDistance(5)
            .build()

        val factory: DataSource.Factory<Int, RepositoryModel> =
            repoDB.repoDao().getAllPaged()
                .map {
                    repoDataMapper.transformDbEntity(it)
                }


        propertyLiveData = LivePagedListBuilder<Int, RepositoryModel>(factory, pagedListConfig)
            .build()
    }

    override fun loadRepositories() {
        initPaging()
    }

    override fun navigateToDetailsScreen() {
        //router.navigateTo(Screens.DetailFragmentScreen())
    }
}