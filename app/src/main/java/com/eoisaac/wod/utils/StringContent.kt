package com.eoisaac.wod.utils

import android.content.Context
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource

/**
 * This sealed class is designed to encapsulate various types of string values, making it versatile for handling dynamic
 * strings, string resources, or empty strings. It is particularly useful when working with ViewModels or other
 * components where you need to manage different types of string data.
 *
 * @property value The string value for the 'DynamicString' case.
 * @property resourceId The string resource ID for the 'StringResource' case.
 * @property args The optional arguments to be used in conjunction with the string resource.
 *
 * @return A string value of the appropriate type.
 */
sealed class StringContent {
    data class DynamicString(val value: String) : StringContent()

    data object Empty : StringContent()

    class StringResource(@StringRes val resourceId: Int, vararg val args: Any) : StringContent()

    @Composable
    fun asString(): String {
        return when (this) {
            is DynamicString -> value
            is StringResource -> stringResource(resourceId, *args)
            is Empty -> ""
        }
    }

    fun asString(context: Context): String {
        return when (this) {
            is DynamicString -> value
            is StringResource -> context.getString(resourceId, *args)
            is Empty -> ""
        }
    }
}