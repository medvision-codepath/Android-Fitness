package com.example.gymapp.ui.workout

enum class WorkoutSetType(private val descriptor: String) {
    WARM_UP("Warm Up"),
    MAIN("Main Lift"),
    BBB("BBB");

    override fun toString(): String = descriptor
}