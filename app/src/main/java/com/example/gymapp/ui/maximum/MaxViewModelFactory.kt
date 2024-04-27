package com.example.gymapp.ui.maximum

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.gymapp.database.TrainingMaxDatabaseDao

class MaxViewModelFactory (
    private val dataSource: TrainingMaxDatabaseDao,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MaxViewModel::class.java)) {
            return MaxViewModel(dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}