package com.example.gymapp.ui.workout

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gymapp.databinding.ListWorkoutSetBinding

class WorkoutAdapter(
    private val data: List<WorkoutSet>,
    private val weekCount: Int,
): RecyclerView.Adapter<WorkoutAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position], weekCount)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    class ViewHolder private constructor(val binding: ListWorkoutSetBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(item: WorkoutSet, weekCount: Int) {
            binding.workoutSet = item
            binding.weekCount = weekCount
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListWorkoutSetBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}