package com.example.githubtestapp.data.repository.dataStore

import com.example.githublibrary.entity.response.DetailResponse
import com.example.githubtestapp.data.db.RepoDB
import com.example.githubtestapp.data.entity.mapper.RepositoryDataMapper
import com.example.githubtestapp.domain.RepositoryModel
import com.upstream.basemvvmimpl.data.BaseDBSource
import io.reactivex.Single
import java.lang.UnsupportedOperationException

class RepoDbDataStore(
    repoDb : RepoDB,
    private val repositoryDataMapper: RepositoryDataMapper
) : BaseDBSource<RepoDB>(repoDb), RepoDataStore {

    override fun saveRepo(repositoryModel: RepositoryModel): Single<Boolean> {
        return Single.fromCallable {

            val entity = repositoryDataMapper.transformModel(repositoryModel)

            val i = db.repoDao().insert(entity)

            i != 0L
        }
    }

    override fun getRepoDetails(fullName: String): Single<DetailResponse> {
        throw UnsupportedOperationException()
    }

    override fun getRepositories(limit: Int, page: Int, findString: String): Single<List<RepositoryModel>> {
        return db.repoDao().getAllRepos()
            .map {
                repositoryDataMapper.transformDbEntities(it)
            }
    }

}