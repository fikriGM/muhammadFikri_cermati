package com.test.cermati.muhammad.fikri.custom

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView

@SuppressLint("AppCompatCustomView")
class SquareImage : ImageView {
    constructor(context : Context) : super(context)
    constructor(context : Context,attrs : AttributeSet) : super (context,attrs)

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        setMeasuredDimension(measuredWidth,measuredWidth)
    }
}