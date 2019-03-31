package com.example.githubtestapp.presentation.di.modules

import android.app.Application
import android.content.Context
import com.example.githubtestapp.GithubApplication
import com.example.githubtestapp.data.executor.JobExecutor
import com.example.githubtestapp.data.repository.DataRepositoryImpl
import com.example.githubtestapp.domain.repository.DataRepository
import com.example.githubtestapp.presentation.UIThread
import com.upstream.basemvvmimpl.domain.executor.PostExecutionThread
import com.upstream.basemvvmimpl.domain.executor.ThreadExecutor
import com.upstream.basemvvmimpl.presentation.di.qualifiers.ApplicationContext
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val application : GithubApplication) {

    @Provides
    @ApplicationContext
    fun provideContext() : Context {
        return application
    }

    @Provides
    fun provideApplication() : Application {
        return application
    }

    @Provides
    @Singleton
    internal fun provideThreadExecutor(jobExecutor: JobExecutor): ThreadExecutor {
        return jobExecutor
    }

    @Provides
    @Singleton
    internal fun providePostExecutionThread(uiThread: UIThread): PostExecutionThread {
        return uiThread
    }

    @Provides
    @Singleton
    fun provideDataRepository(dataRepository: DataRepositoryImpl) : DataRepository {
        return dataRepository
    }
}