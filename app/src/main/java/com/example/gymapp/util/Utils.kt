package com.example.gymapp.util

import com.example.gymapp.ui.home.WorkoutType
import com.example.gymapp.ui.workout.WorkoutSet
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.*

object Utils {

    fun roundDownToNearestIncrement(weight: Float, increment: Float) : Float {
        return kotlin.math.floor(weight / increment) * increment
    }

    fun formatWorkoutInfo(workoutType: WorkoutType, weekCount: Int): String {
        return String.format("%s - %s", formatWorkoutTypeString(workoutType), formatWeekString(weekCount))
    }

    private fun formatWorkoutTypeString(workoutType: WorkoutType): String {
        return when (workoutType) {
            WorkoutType.SQUAT -> "Squat"
            WorkoutType.BENCH -> "Bench Press"
            WorkoutType.DEADLIFT -> "Deadlift"
            WorkoutType.OHP -> "Overhead Press"
        }
    }

    private fun formatWeekString(weekCount: Int): String {
        return if (weekCount < 4) {
            "Week $weekCount"
        } else {
            "Deload"
        }
    }

    fun formatSetDetailsWithAmrap(workoutSet: WorkoutSet): String {
        return if (workoutSet.weekCount != 4 && workoutSet.setCount == 6) {
            "${workoutSet.weight.format(1)} kg x ${workoutSet.repCount}+"
        } else {
            "${workoutSet.weight.format(1)} kg x ${workoutSet.repCount}"
        }
    }

    private fun Float.format(digits: Int) = "%.${digits}f".format(this)

    fun formatSetDetails(set: com.example.gymapp.database.Set): String {
        return "${set.weight.format(1)} kg x ${set.repCount}"
    }

    fun formatTrainingMax(trainingMax: Float): String {
        return "TM ${trainingMax.format(1)} kg"
    }

    fun formatDate(date: LocalDateTime): String {
        val dayFormatted = date.dayOfWeek.getDisplayName(TextStyle.FULL, Locale.getDefault())
        val monthFormatted = date.month.getDisplayName(TextStyle.FULL, Locale.getDefault())

        return "${dayFormatted}, ${date.dayOfMonth} $monthFormatted ${date.year}"
    }
}