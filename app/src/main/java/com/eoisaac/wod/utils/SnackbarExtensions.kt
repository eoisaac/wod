package com.eoisaac.wod.utils

import android.view.Gravity
import android.widget.FrameLayout
import com.google.android.material.snackbar.Snackbar

fun Snackbar.setTopGravity(): Snackbar {
    val params = view.layoutParams as FrameLayout.LayoutParams
    params.gravity = Gravity.TOP
    view.layoutParams = params
    return this
}
