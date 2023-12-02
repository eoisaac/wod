package com.eoisaac.wod.utils

import androidx.core.content.ContextCompat
import com.eoisaac.wod.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder

fun MaterialAlertDialogBuilder.setShape(): MaterialAlertDialogBuilder {
    val shapeDrawable = ContextCompat.getDrawable(context, R.drawable.alerts_shape)
    setBackground(shapeDrawable)
    return this
}