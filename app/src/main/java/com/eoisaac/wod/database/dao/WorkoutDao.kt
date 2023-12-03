package com.eoisaac.wod.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.eoisaac.wod.database.models.Workout
import com.eoisaac.wod.database.models.WorkoutWithExercises

@Dao
interface WorkoutDao {

    /**
     * Insert a workout
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(workout: Workout): Long

    /**
     * Delete a workout
     */
    @Delete
    fun delete(workout: Workout): Int

    /**
     * Get all workouts with exercises based on the given [weekDay]
     */
    @Transaction
    @Query("SELECT * FROM workouts WHERE id IN (SELECT workout_id FROM workout_has_week_day WHERE week_day = :weekDay)")
    fun getWorkoutsWithExercisesByWeekDay(weekDay: String): LiveData<List<WorkoutWithExercises>>

    /**
     * Get all workouts with exercises
     */
    @Transaction
    @Query("SELECT * FROM workouts")
    fun getWorkoutsWithExercises(): LiveData<List<WorkoutWithExercises>>
}