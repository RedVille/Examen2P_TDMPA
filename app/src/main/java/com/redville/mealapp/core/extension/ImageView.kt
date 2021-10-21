package com.redville.mealapp.core.extension

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load
import coil.transform.CircleCropTransformation
import com.redville.mealapp.R

@BindingAdapter("loadFromURLCircular")
fun ImageView.loadFromURLCircular(url: String) = this.load(url) {
    crossfade(true)
    placeholder(R.drawable.ic_default)
    transformations(CircleCropTransformation())
}

@BindingAdapter("loadFromUrl")
fun ImageView.loadFromURL(url: String) = this.load(url) {
    crossfade(true)
    placeholder(R.drawable.ic_default)
}