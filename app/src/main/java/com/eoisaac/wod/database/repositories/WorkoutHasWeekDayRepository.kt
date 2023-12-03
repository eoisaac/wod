package com.eoisaac.wod.database.repositories

import com.eoisaac.wod.database.dao.WorkoutHasWeekDayDao
import com.eoisaac.wod.database.models.WorkoutHasWeekDay

/**
 * Repository class to handle CRUD operations on the WorkoutHasWeekDay table
 */
class WorkoutHasWeekDayRepository(private val workoutHasWeekDayDao: WorkoutHasWeekDayDao) {

    fun insert(workoutHasWeekDay: WorkoutHasWeekDay): Long {
        return workoutHasWeekDayDao.insert(workoutHasWeekDay)
    }
}