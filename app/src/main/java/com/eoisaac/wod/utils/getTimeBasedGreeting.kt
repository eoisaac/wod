package com.eoisaac.wod.utils

import android.content.Context
import com.eoisaac.wod.R
import java.util.*


/**
 * Returns a time-based greeting based on the given date.
 *
 * @param context The context to use for retrieving the string resources.
 * @param date The date for which the greeting is generated.
 * @return A greeting string based on the time of day.
 */
fun getTimeBasedGreeting(context: Context, date: Date): String {
    val calendar = Calendar.getInstance()
    calendar.time = date
    val hour = calendar.get(Calendar.HOUR_OF_DAY)

    val greetingResourceId = when (hour) {
        in 6..11 -> R.string.morning_greeting
        in 12..17 -> R.string.afternoon_greeting
        else -> R.string.evening_greeting
    }
    return context.resources.getString(greetingResourceId)
}