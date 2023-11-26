package com.eoisaac.wod.utils

import com.eoisaac.wod.R
import java.util.*

class Greeting {
    companion object {
        fun getTimeBasedGreeting(date: Date = Date()): StringContent.StringResource {
            val hour = DateUtils.getDateHour(date)
            val greetingResourceId = when (hour) {
                in 6..11 -> R.string.morning_greeting
                in 12..17 -> R.string.afternoon_greeting
                else -> R.string.evening_greeting
            }

            return StringContent.StringResource(resourceId = greetingResourceId)
        }
    }
}