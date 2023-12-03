package com.eoisaac.wod.entities

/**
 * Enum class to represent the days of the week
 * @param day The name of the day
 * @param index The index of the day
 */
enum class WeekDays(val day: String, val index: Int) {
    SUNDAY("SUNDAY", 1),
    MONDAY("MONDAY", 2),
    TUESDAY("TUESDAY", 3),
    WEDNESDAY("WEDNESDAY", 4),
    THURSDAY("THURSDAY", 5),
    FRIDAY("FRIDAY", 6),
    SATURDAY("SATURDAY", 7);

    companion object {
        fun fromIndex(index: Int): WeekDays? {
            return entries.find { it.index == index }
        }
    }
}