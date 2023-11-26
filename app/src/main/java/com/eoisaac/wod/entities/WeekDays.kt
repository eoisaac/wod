package com.eoisaac.wod.entities

enum class WeekDays(val day: String, val index: Int) {
    SUNDAY("SUNDAY ", 1),
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