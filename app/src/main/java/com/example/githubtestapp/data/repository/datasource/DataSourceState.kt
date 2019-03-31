package com.example.githubtestapp.data.repository.datasource

enum class State {
    DONE(), LOADING(), ERROR();

    private var message = ""

    fun setMessage(message : String) {
        this.message = message
    }

    fun getMessage() : String {
        return message
    }

}