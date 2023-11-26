package com.eoisaac.wod.database.models

import androidx.room.*

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
