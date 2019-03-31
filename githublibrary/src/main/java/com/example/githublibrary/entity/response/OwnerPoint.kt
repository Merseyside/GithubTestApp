package com.example.githublibrary.entity.response

import com.google.gson.annotations.SerializedName

data class OwnerPoint(
    @SerializedName("login")
    val login: String,

    @SerializedName("avatar_url")
    val avatarUrl: String
)