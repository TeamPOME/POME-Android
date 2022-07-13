package com.teampome.pome.util.decorate

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CustomItemDecorator(private val mSpacing: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)

        val index = (view.layoutParams as GridLayoutManager.LayoutParams).spanIndex
        if (index == 0) {
            //좌측 Spacing 절반
            outRect.right = mSpacing / 2
        } else {
            //우측 Spacing 절반
            outRect.left = mSpacing / 2
        }
    }
}

