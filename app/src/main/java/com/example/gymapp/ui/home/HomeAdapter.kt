package com.example.gymapp.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gymapp.databinding.ListWorkoutDayBinding

class HomeAdapter(private val clickListener: WorkoutDayListener): RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    private var data: List<WorkoutDay>

    init {
        val workoutDays = mutableListOf<WorkoutDay>()
        val workoutTypes = enumValues<WorkoutType>()

        for (weekCount in 1..4) {
            for (type in workoutTypes) {
                workoutDays.add(WorkoutDay(type, weekCount))
            }
        }
        data = workoutDays
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position], clickListener)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    class ViewHolder private constructor(val binding: ListWorkoutDayBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: WorkoutDay, clickListener: WorkoutDayListener) {
            binding.workoutDay = item
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListWorkoutDayBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}

class WorkoutDayListener(val clickListener: (workoutDay: WorkoutDay) -> Unit) {
    fun onClick(workoutDay: WorkoutDay) = clickListener(workoutDay)
}