package com.teampome.pome.util

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class FriendsProfileItemDecorator(val spacing:Int):RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        val position=parent.getChildLayoutPosition(view)
        val count=state.itemCount

        if(position==count-1){
            outRect.right=16
        }else{
            outRect.right=spacing
        }
    }
}