package com.example.gymapp.ui.home

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.gymapp.R

@BindingAdapter("workoutTypeName")
fun TextView.setExerciseTypeName(workoutType: WorkoutType) {
    text = workoutType.toString()
}

@BindingAdapter("weekCount")
fun TextView.setWeekCount(week: Int) {
    val weekText = if (week < 4) {
        "Week $week"
    } else {
        "Deload"
    }
    text = weekText
}

@BindingAdapter("workoutTypeImage")
fun ImageView.setExerciseTypeImage(workoutType: WorkoutType) {
    setImageResource(
        when (workoutType) {
            WorkoutType.SQUAT -> R.drawable.squat
            WorkoutType.BENCH -> R.drawable.bench_press
            WorkoutType.DEADLIFT -> R.drawable.deadlift
            WorkoutType.OHP -> R.drawable.overhead_press
        }
    )
}