package com.eoisaac.wod.database.repositories

import android.util.Log
import androidx.lifecycle.LiveData
import com.eoisaac.wod.database.dao.WorkoutDao
import com.eoisaac.wod.database.models.Workout
import com.eoisaac.wod.database.models.WorkoutWithExercises
import com.eoisaac.wod.entities.WeekDays

class WorkoutRepository(private val workoutDao: WorkoutDao) {

    fun insert(workout: Workout): Long {
        return workoutDao.insert(workout)
    }

    fun delete(workout: Workout): Int {
        return workoutDao.delete(workout)
    }

    fun getWorkoutsWithExercisesByWeekDay(weekDay: WeekDays): LiveData<List<WorkoutWithExercises>> {
        return workoutDao.getWorkoutsWithExercisesByWeekDay(weekDay.day)
    }

    fun getAllWorkoutsWithExercises(): LiveData<List<WorkoutWithExercises>> {
        return workoutDao.getWorkoutsWithExercises()
    }
}