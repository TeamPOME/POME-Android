package com.teampome.pome.util

import android.view.View
import android.widget.ImageView
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import androidx.databinding.adapters.AutoCompleteTextViewBindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("android:visibility")
fun View.setVisibility(isVisible: Boolean) {
    this.isVisible = isVisible
}
@BindingAdapter("android:invisibility")
fun View.setInvisibility(isInvisible: Boolean) {
    this.isInvisible = isInvisible
}
@BindingAdapter("android:setImage")
fun ImageView.setImage(imgUrl: Int?) {
    this.let {
        Glide.with(context)
            .load(imgUrl)
            .into(this)
    }
}
