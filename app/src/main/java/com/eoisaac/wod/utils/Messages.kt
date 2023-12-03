package com.eoisaac.wod.utils

import com.eoisaac.wod.R
import java.util.*

/**
 * Utility class to handle messages
 */
class Messages {
    companion object {

        /**
         * Get the greeting based on the current time
         * @param date The date to get the hour from
         * @return The greeting based on the current time
         */
        fun getTimeBasedGreeting(date: Date = Date()): StringContent.StringResource {
            val hour = DateUtils.getDateHour(date)
            val greetingResourceId = when (hour) {
                in 6..11 -> R.string.morning_greeting
                in 12..17 -> R.string.afternoon_greeting
                else -> R.string.evening_greeting
            }

            return StringContent.StringResource(resourceId = greetingResourceId)
        }

        /**
         * Get the completion message based on the current time
         * @param percent The percentage of completion
         * @return The completion message based on the current time
         */
        fun getCompletionMessage(percent: Int): StringContent.StringResource {
            val completionMessageResourceId = when (percent) {
                in 0..25 -> R.string.completion_message_0
                in 26..49 -> R.string.completion_message_25
                in 50..75 -> R.string.completion_message_50
                in 76..99 -> R.string.completion_message_75
                else -> R.string.completion_message_100
            }

            return StringContent.StringResource(resourceId = completionMessageResourceId)
        }
    }
}