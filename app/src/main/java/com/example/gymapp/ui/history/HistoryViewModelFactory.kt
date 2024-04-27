package com.example.gymapp.ui.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.gymapp.database.SetDatabaseDao

class HistoryViewModelFactory(private val setDataSource: SetDatabaseDao) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(HistoryViewModel::class.java)) {
                return HistoryViewModel(setDataSource) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }