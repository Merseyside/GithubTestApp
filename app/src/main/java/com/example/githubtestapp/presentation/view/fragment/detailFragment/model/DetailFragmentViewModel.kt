package com.example.githubtestapp.presentation.view.fragment.detailFragment.model

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.example.githublibrary.entity.response.DetailResponse
import com.example.githubtestapp.domain.interactor.GetRepoDetailsInteractor
import com.example.githubtestapp.presentation.base.BaseGithubViewModel
import com.upstream.basemvvmimpl.domain.interactor.DefaultSingleObserver
import ru.terrakok.cicerone.Router

class DetailFragmentViewModel(
    private val router: Router,
    private val getRepoDetailsUseCase: GetRepoDetailsInteractor
) : BaseGithubViewModel() {

    val nameObservableField = ObservableField<String>()
    val descriptionObservableField = ObservableField<String>()
    val avatarObservableField = ObservableField<String>()
    val ownerObservableField = ObservableField<String>()
    val forksObservableField = ObservableField<String>()
    val starsObservableField = ObservableField<String>()
    val dateObservableField = ObservableField<String>()

    val repoDetailsLiveData = MutableLiveData<DetailResponse>()

    override fun clearDisposables() {
        getRepoDetailsUseCase.clear()
    }

    override fun dispose() {
        getRepoDetailsUseCase.dispose()
    }

    fun loadDetails(fullName: String) {
        showProgress()

        getRepoDetailsUseCase.execute(DetailObserver(), GetRepoDetailsInteractor.Params(fullName))
    }

    fun showDetails(detailsResponse: DetailResponse) {
        nameObservableField.set(detailsResponse.name)
        descriptionObservableField.set(detailsResponse.description)
        avatarObservableField.set(detailsResponse.owner.avatarUrl)
        ownerObservableField.set(detailsResponse.owner.login)
        forksObservableField.set(detailsResponse.forksCount.toString())
        starsObservableField.set(detailsResponse.starsCount.toString())
        dateObservableField.set(detailsResponse.date)
    }

    fun getName() : String {
        return repoDetailsLiveData.value!!.name
    }

    fun getDescription() : String {
        return repoDetailsLiveData.value!!.description
    }

    fun getAvatarUrl() : String {
        return repoDetailsLiveData.value!!.owner.avatarUrl
    }

    fun getOwner() : String {
        return repoDetailsLiveData.value!!.owner.login
    }

    fun getForksCount() : String {
        return repoDetailsLiveData.value!!.forksCount.toString()
    }

    fun getStarsCount() : String {
        return repoDetailsLiveData.value!!.starsCount.toString()
    }

    fun getDate() : String {
        return repoDetailsLiveData.value!!.date
    }

    private inner class DetailObserver : DefaultSingleObserver<DetailResponse>() {

        override fun onSuccess(obj: DetailResponse) {
            super.onSuccess(obj)

            repoDetailsLiveData.value = obj
        }

        override fun onError(throwable: Throwable) {
            super.onError(throwable)

            showErrorMsg(throwable.message ?: "Unknown error")
        }
    }

}