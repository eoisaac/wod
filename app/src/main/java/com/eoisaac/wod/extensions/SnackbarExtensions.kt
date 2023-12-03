package com.eoisaac.wod.extensions

import android.view.Gravity
import android.widget.FrameLayout
import com.eoisaac.wod.R
import com.google.android.material.snackbar.Snackbar


/**
 * Extension function to set the gravity of the snackbar to the top
 */
fun Snackbar.setTopGravity(): Snackbar {
    val params = view.layoutParams as FrameLayout.LayoutParams
    params.gravity = Gravity.TOP
    view.layoutParams = params
    return this
}

/**
 * Extension function to set the shape of the snackbar, using the shape drawable
 */
fun Snackbar.setShape(): Snackbar {
    val shapeDrawable = context.getDrawable(R.drawable.alerts_shape)
    view.background = shapeDrawable
    return this
}
