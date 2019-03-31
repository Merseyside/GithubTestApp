package com.example.githubtestapp.data.entity.mapper

import com.example.githublibrary.entity.response.RepoResponse
import com.example.githubtestapp.data.db.RepoEntity
import com.example.githubtestapp.domain.RepositoryModel
import javax.inject.Inject

class RepositoryDataMapper @Inject constructor() {

    fun transformCloudData(repoResponseList: List<RepoResponse>) : List<RepositoryModel> {

        return repoResponseList.map {
            RepositoryModel(
                it.id,
                it.name,
                it.fullName,
                it.description ?: "No description"
            )
        }
    }

    fun transformDbEntities(repoResponseList: List<RepoEntity>) : List<RepositoryModel> {

        return repoResponseList.map {
            transformDbEntity(it)
        }
    }

    fun transformDbEntity(repoEntity: RepoEntity) : RepositoryModel {

        return RepositoryModel(
            repoEntity.id,
            repoEntity.name,
            repoEntity.fullName,
            repoEntity.description
        )
    }
}