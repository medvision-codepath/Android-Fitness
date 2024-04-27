package com.example.gymapp.ui.history

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gymapp.databinding.ListHistorySessionBinding
import com.example.gymapp.util.Utils
import com.example.gymapp.util.Utils.formatDate
import com.example.gymapp.util.Utils.formatTrainingMax
import com.example.gymapp.util.Utils.formatWorkoutInfo

class HistoryAdapter(val data: List<Session>): RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    class ViewHolder private constructor(val binding: ListHistorySessionBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Session) {
            binding.date.text = formatDate(item.date)
            binding.workoutTypeWeekCount.text = formatWorkoutInfo(item.workoutType, item.weekCount)
            binding.trainingMax.text = formatTrainingMax(item.trainingMax)

            // sort the sets?
            binding.set1Details.text = Utils.formatSetDetails(item.sets[0])
            binding.set2Details.text = Utils.formatSetDetails(item.sets[1])
            binding.set3Details.text = Utils.formatSetDetails(item.sets[2])
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListHistorySessionBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}