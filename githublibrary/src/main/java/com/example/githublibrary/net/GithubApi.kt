package com.example.githublibrary.net

import android.content.Context
import com.example.githublibrary.entity.response.DetailResponse
import com.example.githublibrary.entity.response.RepoResponse
import io.reactivex.Single

class GithubApi(context : Context) {

    private val creator = GithubResponseCreator(context)

    fun getPage(limit: Int, page: Int, findString: String?) : Single<List<RepoResponse>> {
        return creator.getPage(limit, page, findString)
    }

    fun getRepoDetails(fullName: String) : Single<DetailResponse> {
        return creator.getRepoDetails(fullName)
    }
}