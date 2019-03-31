package com.example.githubtestapp.data.repository.dataStore

import com.example.githublibrary.entity.response.DetailResponse
import com.example.githubtestapp.domain.RepositoryModel
import io.reactivex.Single

interface GithubDataStore {

    fun getRepositories(limit: Int, page: Int, findString: String) : Single<List<RepositoryModel>>

    fun getRepoDetails(fullName: String) : Single<DetailResponse>
}