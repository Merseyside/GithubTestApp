package com.example.githubtestapp

import android.app.Application
import com.example.githubtestapp.presentation.di.components.AppComponent
import com.example.githubtestapp.presentation.di.components.DaggerAppComponent
import com.example.githubtestapp.presentation.di.modules.AppModule
import com.example.githubtestapp.presentation.di.modules.NavigationModule
import com.example.githubtestapp.presentation.di.modules.RoomModule

class GithubApplication : Application() {

    companion object {

        private lateinit var instance: GithubApplication

        fun getInstance() : GithubApplication {
            return instance
        }
    }

    lateinit var appComponent : AppComponent
        private set

    override fun onCreate() {
        super.onCreate()

        instance = this

        appComponent = buildComponent()
        appComponent.inject(this)
    }

    private fun buildComponent() : AppComponent {
        return DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .navigationModule(NavigationModule())
            .roomModule(RoomModule(this))
            .build()
    }


}