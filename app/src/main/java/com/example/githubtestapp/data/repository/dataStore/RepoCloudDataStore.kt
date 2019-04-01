package com.example.githubtestapp.data.repository.dataStore

import com.example.githublibrary.entity.response.DetailResponse
import com.example.githublibrary.net.GithubApi
import com.example.githubtestapp.data.entity.mapper.RepositoryDataMapper
import com.example.githubtestapp.domain.RepositoryModel
import io.reactivex.Single

class RepoCloudDataStore(
    private val api : GithubApi,
    private val repositoryDataMapper: RepositoryDataMapper
) : RepoDataStore {

    override fun saveRepo(repositoryModel: RepositoryModel): Single<Boolean> {
        throw UnsupportedOperationException()
    }

    override fun getRepoDetails(fullName: String): Single<DetailResponse> {
        return api.getRepoDetails(fullName)
    }

    override fun getRepositories(limit: Int, page: Int, findString: String): Single<List<RepositoryModel>> {
        return api.getPage(limit, page, findString)
            .map {
                repositoryDataMapper.transformCloudData(it)
            }
    }

}