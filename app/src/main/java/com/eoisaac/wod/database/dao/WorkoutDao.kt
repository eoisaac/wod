package com.eoisaac.wod.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.eoisaac.wod.database.models.Workout
import com.eoisaac.wod.database.models.WorkoutWithExercises

@Dao
interface WorkoutDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(workout: Workout): Long

    @Delete
    fun delete(workout: Workout): Int

    @Transaction
    @Query("SELECT * FROM workouts WHERE id IN (SELECT workout_id FROM workout_has_week_day WHERE week_day = :weekDay)")
    fun getWorkoutsWithExercisesByWeekDay(weekDay: String): LiveData<List<WorkoutWithExercises>>

    @Transaction
    @Query("SELECT * FROM workouts")
    fun getWorkoutsWithExercises(): LiveData<List<WorkoutWithExercises>>
}