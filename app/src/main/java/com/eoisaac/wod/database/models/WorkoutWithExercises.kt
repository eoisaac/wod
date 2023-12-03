package com.eoisaac.wod.database.models

import androidx.room.Embedded
import androidx.room.Relation

/**
 * A data class representing a workout with its exercises.
 *
 * @property workout The workout.
 * @property exercises The exercises in the workout.
 * @property workoutHasWeekDay The week days this workout is scheduled for.
 */
data class WorkoutWithExercises(
    @Embedded val workout: Workout,
    @Relation(
        parentColumn = "id",
        entityColumn = "workout_id"
    )
    val exercises: List<Exercise>,
    @Relation(
        parentColumn = "id",
        entityColumn = "workout_id"
    )
    val workoutHasWeekDay: List<WorkoutHasWeekDay>
)
