package com.eoisaac.wod.extensions

import androidx.core.content.ContextCompat
import com.eoisaac.wod.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder

/**
 * Extension function to set the shape of the alert dialog, using the shape drawable
 */
fun MaterialAlertDialogBuilder.setShape(): MaterialAlertDialogBuilder {
    val shapeDrawable = ContextCompat.getDrawable(context, R.drawable.alerts_shape)
    setBackground(shapeDrawable)
    return this
}