package com.eoisaac.wod.database.repositories

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.eoisaac.wod.database.dao.WorkoutDao
import com.eoisaac.wod.database.models.Workout
import com.eoisaac.wod.database.models.WorkoutWithExercises

class WorkoutRepository(private val workoutDao: WorkoutDao) {

    fun insert(workout: Workout): Long {
        return workoutDao.insert(workout)
    }

    fun delete(workout: Workout): Int {
        return workoutDao.delete(workout)
    }

    fun getById(id: Long): Workout {
        return workoutDao.getById(id)
    }

    fun getByWeekDay(weekDay: String): Workout {
        return workoutDao.getByWeekDay(weekDay)
    }

    fun getWorkoutWithExercisesById(id: Long): WorkoutWithExercises {
        return workoutDao.getWorkoutWithExercisesById(id)
    }

    fun getWorkoutsWithExercisesByWeekDay(weekDay: String): LiveData<List<WorkoutWithExercises>> {
        return workoutDao.getWorkoutsWithExercisesByWeekDay(weekDay)
    }

    fun getWorkoutsWithExercisesByWeekDay2(weekDay: String): LiveData<List<WorkoutWithExercises>> {
        val workoutsList = workoutDao.getWorkoutsWithExercises()
        return workoutsList.map { workouts ->
            workouts.filter { workout -> weekDay in workout.workout.weekDays }
        }
    }

    fun getAllWorkoutsWithExercises(): LiveData<List<WorkoutWithExercises>> {
        return workoutDao.getWorkoutsWithExercises()
    }
}