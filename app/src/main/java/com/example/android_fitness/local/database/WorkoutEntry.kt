package com.example.android_fitness.local.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class WorkoutEntry (
    @PrimaryKey(autoGenerate = true) val id: Int = 0, // Default value for id
    @ColumnInfo(name = "date") val date: String,
    @ColumnInfo(name = "amount") val amount: Double
)