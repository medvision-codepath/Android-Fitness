package com.example.android_fitness.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.lifecycle.LiveData
import com.example.android_fitness.local.database.WorkoutEntry
@Dao
interface WorkoutEntryDao {
    @Insert
    fun insertEntry(workoutEntry: WorkoutEntry)

    @Query("SELECT * FROM workoutentry")
    fun getAllEntries(): LiveData<List<WorkoutEntry>>
}