package com.stone.transition

import android.content.Context
import android.support.v7.widget.CardView
import android.util.AttributeSet
import android.view.ViewGroup

/**
 * 锁定宽高比的CardView
 * Created by xmuSistone on 2016/9/21.
 */
class AspectRatioCardView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : CardView(context, attrs, defStyleAttr) {

    private val ratio = 1.2f

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        if (ratio > 0) {
            val ratioHeight = (measuredWidth * ratio).toInt()
            setMeasuredDimension(measuredWidth, ratioHeight)
            val lp = layoutParams
            lp.height = ratioHeight
            layoutParams = lp
        }
    }
}