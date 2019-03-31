package com.example.githubtestapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [RepoEntity::class], version = 1)
abstract class RepoDB : RoomDatabase() {

    abstract fun repoDao() : RepoDao
}