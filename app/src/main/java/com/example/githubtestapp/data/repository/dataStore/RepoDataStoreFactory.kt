package com.example.githubtestapp.data.repository.dataStore

import android.content.Context
import com.example.githublibrary.net.GithubApi
import com.example.githubtestapp.data.db.RepoDB
import com.example.githubtestapp.data.entity.mapper.RepositoryDataMapper
import com.upstream.basemvvmimpl.presentation.di.qualifiers.ApplicationContext
import javax.inject.Inject

class RepoDataStoreFactory @Inject constructor(
    @ApplicationContext private val context : Context,
    private val repoDB : RepoDB,
    private val repositoryDataMapper: RepositoryDataMapper
) {

    fun create() : GithubDataStore {

        return createCloudDataStore()
    }

    fun createCloudDataStore() : CloudDataStore {
        return CloudDataStore(GithubApi(context), repositoryDataMapper)
    }

    fun createDbDataStore() : RepoDbDataStore {
        return RepoDbDataStore(repoDB, repositoryDataMapper)
    }
}