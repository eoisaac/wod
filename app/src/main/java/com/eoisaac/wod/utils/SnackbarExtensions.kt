package com.eoisaac.wod.utils

import android.view.Gravity
import android.widget.FrameLayout
import com.eoisaac.wod.R
import com.google.android.material.snackbar.Snackbar

fun Snackbar.setTopGravity(): Snackbar {
    val params = view.layoutParams as FrameLayout.LayoutParams
    params.gravity = Gravity.TOP
    view.layoutParams = params
    return this
}

fun Snackbar.setShape(): Snackbar {
    val shapeDrawable = context.getDrawable(R.drawable.alerts_shape)
    view.background = shapeDrawable
    return this
}
