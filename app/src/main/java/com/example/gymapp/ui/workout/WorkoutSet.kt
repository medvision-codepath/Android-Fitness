package com.example.gymapp.ui.workout

import com.example.gymapp.util.Utils.roundDownToNearestIncrement

data class WorkoutSet (
    val setType: WorkoutSetType,
    val setCount: Int,
    var weight: Float,
    val repCount: Int,
    val weekCount: Int,
) {
    init {
        weight = roundDownToNearestIncrement(weight, 2.5f)
    }

}
