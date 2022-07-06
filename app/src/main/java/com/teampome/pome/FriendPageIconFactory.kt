package com.teampome.pome

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import com.skydoves.balloon.ArrowOrientation
import com.skydoves.balloon.Balloon
import com.skydoves.balloon.BalloonAnimation

class FriendPageIconFactory : Balloon.Factory() {
    override fun create(context: Context, lifecycle: LifecycleOwner?): Balloon {
        return Balloon.Builder(context)
            .setWidthRatio(0.8f)
            .setHeight(50)
            .setWidth(400)
            .setIsVisibleArrow(false)
            .setArrowOrientation(ArrowOrientation.LEFT)
            .setCornerRadius(8f)
            .setAlpha(0.9f)
            .setTextIsHtml(true)
            .setLayout(R.layout.item_icon_list)
            .setBackgroundColorResource(R.color.white)
            .setBalloonAnimation(BalloonAnimation.CIRCULAR)
            .setLifecycleOwner(lifecycle)
            .build()
    }
}