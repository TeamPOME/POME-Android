package com.teampome.pome.util

import android.view.View
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import androidx.databinding.adapters.AutoCompleteTextViewBindingAdapter

@BindingAdapter("android:visibility")
fun View.setVisibility(isVisible: Boolean) {
    this.isVisible = isVisible
}
@BindingAdapter("android:invisibility")
fun View.setInvisibility(isInvisible: Boolean) {
    this.isInvisible = isInvisible
}