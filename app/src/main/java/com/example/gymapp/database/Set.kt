package com.example.gymapp.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.gymapp.ui.home.WorkoutType
import com.example.gymapp.ui.workout.WorkoutSetType
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

@Entity("table_set")
data class Set(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "set_id")
    val setId: Long = 0L,

    @ColumnInfo(name = "date")
    val date: LocalDateTime,

    @ColumnInfo(name = "workout_type")
    val workoutType: WorkoutType,

    @ColumnInfo(name = "workout_set_type")
    val setType: WorkoutSetType,

    @ColumnInfo(name = "week_count")
    val weekCount: Int,

    @ColumnInfo(name = "training_max")
    val trainingMax: Float,

    @ColumnInfo(name = "weight")
    val weight: Float,

    @ColumnInfo(name = "rep_count")
    val repCount: Int,

    @ColumnInfo(name = "session_id")
    val sessionId: UUID
)
