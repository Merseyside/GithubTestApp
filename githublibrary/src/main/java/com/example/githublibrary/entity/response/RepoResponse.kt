package com.example.githublibrary.entity.response

import com.google.gson.annotations.SerializedName


data class RepoResponse(

    @SerializedName("id")
    val id: Long,

    @SerializedName("name")
    val name: String,

    @SerializedName("full_name")
    val fullName: String,

    @SerializedName("description")
    val description: String?
)