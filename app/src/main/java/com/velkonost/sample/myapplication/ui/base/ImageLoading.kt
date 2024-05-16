package com.velkonost.sample.myapplication.ui.base

import android.graphics.drawable.Drawable
import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.velkonost.sample.myapplication.R

@BindingAdapter("imageUrl")
fun ImageView.loadImage(
    imageUrl: String?
) {
    Glide.with(this.context)
        .load(imageUrl)
        .placeholder(R.drawable.ic_launcher_background)
        .into(this)
}