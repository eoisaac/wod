package com.eoisaac.wod.database.models

import androidx.room.*

/**
 * A data class representing a relationship between a workout and a week day
 *
 * @property id The unique identifier of the workout.
 * @property workoutId The id of the workout this exercise belongs to.
 * @property weekDay The week day this workout is scheduled for.
 */
@Entity(
    tableName = "workout_has_week_day", foreignKeys = [
        ForeignKey(
            entity = Workout::class,
            parentColumns = ["id"],
            childColumns = ["workout_id"],
            onDelete = ForeignKey.CASCADE,
        )
    ]
)
data class WorkoutHasWeekDay(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long = 0L,

    @ColumnInfo(name = "workout_id") val workoutId: Long,
    @ColumnInfo(name = "week_day") val weekDay: String,
)
