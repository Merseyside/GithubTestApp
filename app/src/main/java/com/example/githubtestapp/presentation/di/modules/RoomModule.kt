package com.example.githubtestapp.presentation.di.modules

import android.content.Context
import androidx.room.Room
import com.example.githubtestapp.data.db.RepoDB
import com.upstream.basemvvmimpl.presentation.di.qualifiers.ApplicationContext
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class RoomModule(@ApplicationContext val context : Context) {

    @Singleton
    @Provides
    internal fun provideCryptoDatabase(): RepoDB {
        return Room.databaseBuilder(context, RepoDB::class.java, "repo-db")
            .build()
    }
}