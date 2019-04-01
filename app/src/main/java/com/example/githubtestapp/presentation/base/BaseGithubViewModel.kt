package com.example.githubtestapp.presentation.base

import android.content.Context
import android.os.Bundle
import com.upstream.basemvvmimpl.presentation.model.BaseViewModel

abstract class BaseGithubViewModel(bundle : Bundle? = null) : BaseViewModel(bundle) {

    override fun updateLanguage(context: Context) {}

    override fun onCleared() {
        super.onCleared()

        dispose()
    }
}