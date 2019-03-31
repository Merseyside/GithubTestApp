package com.example.githublibrary.net

import com.example.githublibrary.entity.response.DetailResponse
import com.example.githublibrary.entity.response.RepoResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap

interface GithubRest {

    @GET("repositories")
    fun getRepositories(@QueryMap map : Map<String, String>) : Single<List<RepoResponse>>

    @GET("repos/{owner}/{repo}")
    fun getRepoDetails(@Path("owner") owner: String, @Path("repo") repo: String)
            : Single<DetailResponse>
}