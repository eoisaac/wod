package com.eoisaac.wod.utils

import com.eoisaac.wod.entities.WeekDays
import java.util.*

class DateUtils {
    companion object {
        fun getDateHour(date: Date): Int {
            val calendar = Calendar.getInstance()
            calendar.time = date
            return calendar.get(Calendar.HOUR_OF_DAY)
        }

        fun getDateWeekDay(date: Date): WeekDays? {
            val calendar = Calendar.getInstance()
            calendar.time = date
            val dayIndex = calendar.get(Calendar.DAY_OF_WEEK)
            return WeekDays.fromIndex(dayIndex)
        }
    }
}