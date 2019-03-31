package com.example.githubtestapp.domain.interactor

import com.example.githublibrary.entity.response.DetailResponse
import com.example.githubtestapp.domain.repository.DataRepository
import com.upstream.basemvvmimpl.domain.executor.PostExecutionThread
import com.upstream.basemvvmimpl.domain.executor.ThreadExecutor
import com.upstream.basemvvmimpl.domain.interactor.SingleUseCase
import io.reactivex.Single
import javax.inject.Inject

class GetRepoDetailsInteractor @Inject constructor(threadExecutor: ThreadExecutor,
                                                   postExecutionThread: PostExecutionThread,
                                                   private val dataRepository: DataRepository)
    : SingleUseCase<DetailResponse, GetRepoDetailsInteractor.Params>(threadExecutor, postExecutionThread) {

    override fun buildUseCaseSingle(params: GetRepoDetailsInteractor.Params?): Single<DetailResponse> {
        return dataRepository.getRepoDetails(params!!.fullName)
    }

    data class Params(
        val fullName: String
    )
}