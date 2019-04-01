package com.example.githubtestapp.domain.interactor

import com.example.githubtestapp.domain.RepositoryModel
import com.example.githubtestapp.domain.repository.DataRepository
import com.upstream.basemvvmimpl.domain.executor.PostExecutionThread
import com.upstream.basemvvmimpl.domain.executor.ThreadExecutor
import com.upstream.basemvvmimpl.domain.interactor.SingleUseCase
import io.reactivex.Single
import javax.inject.Inject

class SaveRepoInteractor @Inject constructor(threadExecutor: ThreadExecutor,
                                             postExecutionThread: PostExecutionThread,
                                             private val dataRepsitory: DataRepository)
    : SingleUseCase<Boolean, SaveRepoInteractor.Params>(threadExecutor, postExecutionThread) {

    override fun buildUseCaseSingle(params: Params?): Single<Boolean> {
        return dataRepsitory.saveRepository(params!!.repository)
    }

    data class Params(
            val repository: RepositoryModel
    )
}