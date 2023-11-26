package com.eoisaac.wod.database.repositories

import com.eoisaac.wod.database.dao.WorkoutHasWeekDayDao
import com.eoisaac.wod.database.models.WorkoutHasWeekDay

class WorkoutHasWeekDayRepository(private val workoutHasWeekDayDao: WorkoutHasWeekDayDao) {

    fun insert(workoutHasWeekDay: WorkoutHasWeekDay): Long {
        return workoutHasWeekDayDao.insert(workoutHasWeekDay)
    }
}