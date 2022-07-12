package com.teampome.pome.presentation.friends

import android.content.Context
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import com.skydoves.balloon.*
import com.teampome.pome.R

class FriendsEmojiBalloon : Balloon.Factory() {
    override fun create(context: Context, lifecycle: LifecycleOwner?): Balloon {
        return createBalloon(context) {
            setLayout(R.layout.item_emoji_balloon)
            setArrowSize(10)
            setArrowOrientation(ArrowOrientation.BOTTOM)
            setArrowPosition(0.5f)
            setWidthRatio(0.55f)
            setHeight(ViewGroup.LayoutParams.WRAP_CONTENT)
            setCornerRadius(4f)
            setBackgroundColor(ContextCompat.getColor(context, R.color.pome_white))
            setBalloonAnimation(BalloonAnimation.CIRCULAR)
            setLifecycleOwner(lifecycle)
        }
    }

}