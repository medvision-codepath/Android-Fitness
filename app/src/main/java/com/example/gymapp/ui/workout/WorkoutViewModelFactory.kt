package com.example.gymapp.ui.workout

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.gymapp.database.SetDatabaseDao
import com.example.gymapp.database.TrainingMaxDatabaseDao
import com.example.gymapp.ui.home.WorkoutType

class WorkoutViewModelFactory(
    private val trainingDataSource: TrainingMaxDatabaseDao,
    private val setDataSource: SetDatabaseDao,
    private val workoutType: WorkoutType,
    private val weekCount: Int
    ) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WorkoutViewModel::class.java)) {
            return WorkoutViewModel(trainingDataSource, setDataSource, workoutType, weekCount) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}