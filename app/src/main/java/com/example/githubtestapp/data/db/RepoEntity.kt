package com.example.githubtestapp.data.db

import androidx.annotation.NonNull
import androidx.recyclerview.widget.DiffUtil
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "repos")
data class RepoEntity(

    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Long,

    @NonNull
    @ColumnInfo(name = "name")
    val name: String,

    @NonNull
    @ColumnInfo(name = "full_name")
    val fullName: String,

    @NonNull
    @ColumnInfo(name = "description")
    val description: String,

    @NonNull
    @ColumnInfo
    val page: Int
)