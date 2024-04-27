package com.example.gymapp.ui.history

import com.example.gymapp.database.Set
import com.example.gymapp.ui.home.WorkoutType
import java.time.LocalDateTime

data class Session(

    val date: LocalDateTime,

    val workoutType: WorkoutType,

    val weekCount: Int,

    val trainingMax: Float,

    val sets: List<Set>
)