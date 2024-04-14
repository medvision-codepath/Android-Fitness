package com.example.android_fitness.app

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.LiveData
import com.example.android_fitness.local.database.AppDatabase
import com.example.android_fitness.local.database.WorkoutEntry
import kotlinx.coroutines.launch
import kotlinx.coroutines.Dispatchers

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val db = AppDatabase.getDatabase(application)
    val allEntries: LiveData<List<WorkoutEntry>>

    init {
        allEntries = db.workoutEntryDao().getAllEntries()
    }

    fun insertEntry(workoutEntry: WorkoutEntry) {
        // Use a background thread for database operations
        viewModelScope.launch(Dispatchers.IO){
            db.workoutEntryDao().insertEntry(workoutEntry)
        }
    }
}