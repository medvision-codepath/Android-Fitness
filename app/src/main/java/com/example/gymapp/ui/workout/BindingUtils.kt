package com.example.gymapp.ui.workout

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.gymapp.util.Utils

@BindingAdapter("workoutSetType")
fun TextView.setWorkoutSetType(workoutSetType: WorkoutSetType) {
    text = workoutSetType.toString()
}

@BindingAdapter("workoutSetDetails")
fun TextView.setWorkoutSetDetails(workoutSet: WorkoutSet) {
    text = Utils.formatSetDetailsWithAmrap(workoutSet)
}