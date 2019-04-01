package com.example.githubtestapp.data.repository.datasource

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.githubtestapp.data.db.RepoEntity
import com.example.githubtestapp.domain.RepositoryModel
import com.example.githubtestapp.domain.repository.DataRepository
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject


class RepositoryDataSourceFactory @Inject constructor(
        private val dataRepository: DataRepository
): DataSource.Factory<Int, RepositoryModel>() {

    private lateinit var repositoryDataSource: RepositoryDataSource

    private val compositeDisposable : CompositeDisposable = CompositeDisposable()

    val propertyDataSourceLiveData = MutableLiveData<RepositoryDataSource>()

    override fun create(): DataSource<Int, RepositoryModel> {
        repositoryDataSource = RepositoryDataSource(dataRepository, compositeDisposable)
        propertyDataSourceLiveData.postValue(repositoryDataSource)
        return repositoryDataSource
    }

    fun setFindString(str: String) {
        repositoryDataSource.setFindString(str)
    }

    fun getCompositeDisposables() = compositeDisposable
}