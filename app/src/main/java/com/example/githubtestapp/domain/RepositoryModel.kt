package com.example.githubtestapp.domain

import androidx.annotation.NonNull
import androidx.recyclerview.widget.DiffUtil

data class RepositoryModel(
    val id: Long,
    val name: String,
    val fullName: String,
    val description: String
) {
    companion object {

        var diffCallback: DiffUtil.ItemCallback<RepositoryModel> = object : DiffUtil.ItemCallback<RepositoryModel>() {
            override fun areItemsTheSame(@NonNull oldItem: RepositoryModel, @NonNull newItem: RepositoryModel): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(@NonNull oldItem: RepositoryModel, @NonNull newItem: RepositoryModel): Boolean {
                return oldItem == newItem
            }
        }
    }



}