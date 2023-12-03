package com.eoisaac.wod.entities

/**
 * Data class to represent the summary of a workout
 * @param totalExercises The total number of exercises in the workout
 * @param totalCompletedExercises The total number of completed exercises in the workout
 * @param completedPercentage The percentage of completed exercises in the workout
 */
data class WorkoutsSummary(
    val totalExercises: Int,
    val totalCompletedExercises: Int,
    val completedPercentage: Int,
)
