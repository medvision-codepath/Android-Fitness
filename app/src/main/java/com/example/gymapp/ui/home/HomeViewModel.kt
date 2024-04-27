package com.example.gymapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gymapp.ui.workout.WorkoutSet

class HomeViewModel : ViewModel() {

    private val _navigateToWorkout = MutableLiveData<WorkoutDay?>()

    val navigateToWorkout: LiveData<WorkoutDay?>
        get() = _navigateToWorkout

    fun onWorkoutClicked(workoutDay: WorkoutDay) {
        _navigateToWorkout.value = workoutDay
    }

    fun doneNavigating() {
        _navigateToWorkout.value = null
    }
}