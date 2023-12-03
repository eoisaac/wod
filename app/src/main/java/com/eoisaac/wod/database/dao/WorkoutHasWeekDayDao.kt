package com.eoisaac.wod.database.dao

import androidx.room.*
import com.eoisaac.wod.database.models.WorkoutHasWeekDay

@Dao
interface WorkoutHasWeekDayDao {
    /**
     * Insert a workoutHasWeekDay
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(workoutHasWeekDay: WorkoutHasWeekDay): Long
}