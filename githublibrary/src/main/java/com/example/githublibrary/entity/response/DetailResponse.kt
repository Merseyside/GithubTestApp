package com.example.githublibrary.entity.response

import com.google.gson.annotations.SerializedName

data class DetailResponse (

    @SerializedName("owner")
    val owner: OwnerPoint,

    @SerializedName("name")
    val name: String,

    @SerializedName("description")
    val description: String,

    @SerializedName("forks_count")
    val forksCount: Int,

    @SerializedName("stargazers_count")
    val starsCount: Int,

    @SerializedName("created_at")
    val date: String
)