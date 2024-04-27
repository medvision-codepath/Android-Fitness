package com.example.gymapp.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity("table_training_max")
data class TrainingMax(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long = 0L,

    @ColumnInfo(name = "squat_max")
    val squatMax: Float,

    @ColumnInfo(name = "bench_max")
    val benchMax: Float,

    @ColumnInfo(name = "deadlift_max")
    val deadliftMax: Float,

    @ColumnInfo(name = "ohp_max")
    val ohpMax: Float
)
