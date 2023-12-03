package com.eoisaac.wod.utils

import com.eoisaac.wod.entities.WeekDays
import java.util.*

/**
 * Utility class to handle date operations
 */
class DateUtils {
    companion object {

        /**
         * Get the current date
         * @param date The date to get the hour from
         * @return The hour of the date
         */
        fun getDateHour(date: Date): Int {
            val calendar = Calendar.getInstance()
            calendar.time = date
            return calendar.get(Calendar.HOUR_OF_DAY)
        }

        /**
         * Get the current date
         * @param date The date to get the day from
         * @return The current week day
         */
        fun getDateWeekDay(date: Date): WeekDays? {
            val calendar = Calendar.getInstance()
            calendar.time = date
            val dayIndex = calendar.get(Calendar.DAY_OF_WEEK)
            return WeekDays.fromIndex(dayIndex)
        }
    }
}