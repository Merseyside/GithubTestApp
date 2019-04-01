package com.example.githublibrary.entity.response

import com.google.gson.annotations.SerializedName

data class SearchResponse(

        @SerializedName("items")
        val items: List<RepoResponse>
)