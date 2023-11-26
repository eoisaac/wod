package com.eoisaac.wod.utils

import com.eoisaac.wod.R
import java.util.*

class Messages {
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

        fun getCompletionMessage(percent: Int): StringContent.StringResource {
            val completionMessageResourceId = when (percent) {
                in 0..25 -> R.string.completion_message_0
                in 26..50 -> R.string.completion_message_25
                in 51..75 -> R.string.completion_message_50
                in 76..99 -> R.string.completion_message_75
                else -> R.string.completion_message_100
            }

            return StringContent.StringResource(resourceId = completionMessageResourceId)
        }
    }
}