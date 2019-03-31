package com.example.githubtestapp.presentation.di.components

import android.app.Application
import android.content.Context
import com.example.githubtestapp.GithubApplication
import com.example.githubtestapp.data.db.RepoDB
import com.example.githubtestapp.domain.repository.DataRepository
import com.example.githubtestapp.presentation.di.modules.AppModule
import com.example.githubtestapp.presentation.di.modules.NavigationModule
import com.example.githubtestapp.presentation.di.modules.RoomModule
import com.upstream.basemvvmimpl.domain.executor.PostExecutionThread
import com.upstream.basemvvmimpl.domain.executor.ThreadExecutor
import com.upstream.basemvvmimpl.presentation.di.qualifiers.ApplicationContext
import dagger.Component
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, NavigationModule::class, RoomModule::class])
interface AppComponent {

    fun inject(application: GithubApplication)

    @ApplicationContext
    fun context() : Context

    fun application() : Application

    fun threadExecutor() : ThreadExecutor

    fun postExecutionThread() : PostExecutionThread

    fun navigation() : NavigatorHolder

    fun router() : Router

    fun dataRepository() : DataRepository

    fun repoDatabase() : RepoDB
}