package com.teampome.pome.presentation.friends.screens

import android.annotation.SuppressLint
import android.content.Context
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import com.skydoves.balloon.*
import com.teampome.pome.R

class FriendsEmojiBalloon : Balloon.Factory() {
    @SuppressLint("ResourceAsColor")
    override fun create(context: Context, lifecycle: LifecycleOwner?): Balloon {
        return createBalloon(context) {
            setLayout(R.layout.item_emoji_balloon)
            setArrowSize(0)
            setArrowOrientation(ArrowOrientation.TOP)
            setArrowPosition(0.5f)
            setWidth(BalloonSizeSpec.WRAP)
            setHeight(BalloonSizeSpec.WRAP)
            setBackgroundColor(ContextCompat.getColor(context, R.color.pome_transparent))
            setBalloonAnimation(BalloonAnimation.CIRCULAR)
            setOverlayColor(R.color.pome_grey_2)
        }
    }
}