package com.example.githubtestapp.data.db

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Query
import io.reactivex.Single

@Dao
interface RepoDao {

    @Query("SELECT * FROM REPOS")
    fun getAll(): LiveData<List<RepoEntity>>

    @Query("SELECT * FROM REPOS")
    fun getAllPaged(): DataSource.Factory<Int, RepoEntity>

    @Query("SELECT * FROM REPOS")
    fun getAllRepos() : Single<List<RepoEntity>>
}