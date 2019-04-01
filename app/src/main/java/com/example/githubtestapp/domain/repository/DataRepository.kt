package com.example.githubtestapp.domain.repository

import com.example.githublibrary.entity.response.DetailResponse
import com.example.githubtestapp.domain.RepositoryModel
import io.reactivex.Single

interface DataRepository {

    fun getRepositories(limit: Int, page: Int, findString: String) : Single<List<RepositoryModel>>

    fun getRepoDetails(fullName: String) : Single<DetailResponse>

    fun saveRepository(repository: RepositoryModel) : Single<Boolean>
}