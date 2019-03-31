package com.example.githubtestapp.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.example.githubtestapp.R

@BindingAdapter("bind:imageUrl")
fun loadImage(iv: ImageView, url: String?) {

    if (url != null && url != "null") {
        Glide.with(iv)
            .load(url)
            .apply(RequestOptions.circleCropTransform())
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(iv)
    }
}