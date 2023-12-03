package com.eoisaac.wod.database.models

import androidx.room.*

/**
 * A data class representing a exercise.
 *
 * @property id The unique identifier of the workout.
 * @property name The name of the workout.
 * @property sets The number of sets in the workout.
 * @property isCompleted Whether the workout is completed or not.
 * @property workoutId The id of the workout this exercise belongs to.
 */
@Entity(
    tableName = "exercises", foreignKeys = [
        ForeignKey(
            entity = Workout::class, onDelete = ForeignKey.CASCADE,
            parentColumns = ["id"], childColumns = ["workout_id"],
        )
    ]
)
data class Exercise(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long = 0L,

    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "sets") val sets: Int,
    @ColumnInfo(name = "is_completed") var isCompleted: Boolean = false,

    @ColumnInfo(name = "workout_id") var workoutId: Long = 0L,
)
