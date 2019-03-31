package com.example.githubtestapp.presentation.view.activity.mainActivity.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubtestapp.domain.RepositoryModel

class SharedViewModel : ViewModel() {

    private val repoContainer = MutableLiveData<RepositoryModel>()

    fun getRepoContainerLiveData() : LiveData<RepositoryModel> {
        return repoContainer
    }

    fun shareRepo(repo : RepositoryModel) {
        repoContainer.value = repo
    }

}