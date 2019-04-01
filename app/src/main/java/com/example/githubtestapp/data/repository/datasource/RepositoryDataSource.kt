package com.example.githubtestapp.data.repository.datasource

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.example.githubtestapp.data.db.RepoEntity
import com.example.githubtestapp.domain.RepositoryModel
import com.example.githubtestapp.domain.repository.DataRepository
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Action
import io.reactivex.schedulers.Schedulers

class RepositoryDataSource(
    private val dataRepository: DataRepository,
    private val compositeDisposable: CompositeDisposable
) : PageKeyedDataSource<Int, RepositoryModel>() {

    private val TAG = javaClass.simpleName

    private val startPage = 1

    companion object {
        private var findString = ""
    }


    var state: MutableLiveData<State> = MutableLiveData()

    private var retryCompletable: Completable? = null

    @SuppressLint("CheckResult")
    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, RepositoryModel>) {
        updateState(State.LOADING, "")

        dataRepository.getRepositories(params.requestedLoadSize, startPage, findString)
            .doOnSubscribe {
                compositeDisposable.add(it)
            }
            .subscribe({ result ->
                callback.onResult(result, null, startPage + 1)
            }, { throwable ->
                throwable.printStackTrace()

                updateState(State.ERROR, throwable.message ?: "Unknown error")
                setRetry(Action { loadInitial(params, callback) })
            })
    }

    @SuppressLint("CheckResult")
    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, RepositoryModel>) {

        dataRepository.getRepositories(params.requestedLoadSize, params.key, findString)
            .doOnSubscribe {
                compositeDisposable.add(it)
            }
            .subscribe({ result ->
                callback.onResult(result, params.key + 1)
            }, { throwable ->
                updateState(State.ERROR, throwable.message ?: "Unknown error")
                setRetry(Action { loadAfter(params, callback) })
            })
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, RepositoryModel>) {}

    private fun updateState(state: State, message : String) {
        state.setMessage(message)
        this.state.postValue(state)
    }

    fun retry() {
        if (retryCompletable != null) {
            compositeDisposable.add(retryCompletable!!
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe())
        }
    }

    private fun setRetry(action: Action?) {
        retryCompletable = if (action == null) {
            null
        } else {
            Completable.fromAction(action)
        }
    }

    fun setFindString(str: String) {
        findString = str
    }

}