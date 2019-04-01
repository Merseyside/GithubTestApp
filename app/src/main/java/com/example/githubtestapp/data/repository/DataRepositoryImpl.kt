package com.example.githubtestapp.data.repository

import com.example.githublibrary.entity.response.DetailResponse
import com.example.githubtestapp.data.exception.NoNetworkConnectionException
import com.example.githubtestapp.data.repository.dataStore.RepoDataStoreFactory
import com.example.githubtestapp.domain.RepositoryModel
import com.example.githubtestapp.domain.repository.DataRepository
import com.example.githubtestapp.utils.Utils
import io.reactivex.Single
import javax.inject.Inject

class DataRepositoryImpl @Inject constructor(
    private val repoDataStoreFactory: RepoDataStoreFactory
) : DataRepository {

    override fun saveRepository(repository: RepositoryModel): Single<Boolean> {
        val dataSource = repoDataStoreFactory.createDbDataStore()

        return dataSource.saveRepo(repository)
    }

    override fun getRepoDetails(fullName: String): Single<DetailResponse> {
        return if (Utils.isOnline()) {
            val dataStore = repoDataStoreFactory.createCloudDataStore()

            return dataStore.getRepoDetails(fullName)
        } else {
            Single.error(NoNetworkConnectionException())
        }
    }

    override fun getRepositories(limit: Int, page: Int, findString: String): Single<List<RepositoryModel>> {

        return if (Utils.isOnline()) {
            val dataStore = repoDataStoreFactory.create()

            return dataStore.getRepositories(limit, page, findString)
        } else {
            Single.error(NoNetworkConnectionException())
        }
    }
}