package com.example.android_fitness.app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.android_fitness.R
import com.example.android_fitness.local.database.WorkoutEntry
import java.util.Locale

class WorkoutEntryAdapter(private var entries: List<WorkoutEntry>) : RecyclerView.Adapter<WorkoutEntryAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val dateTextView: TextView = view.findViewById(R.id.date_text)
        private val amountTextView: TextView = view.findViewById(R.id.amount_text)

        fun bind(entry: WorkoutEntry) {
            dateTextView.text = entry.date
            amountTextView.text = String.format(Locale.getDefault(),"", entry.amount)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =  LayoutInflater.from(parent.context).inflate(R.layout.workout_entry_item, parent, false )
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder:ViewHolder,position: Int) {
        holder.bind(entries[position])
    }

    override fun getItemCount(): Int = entries.size

    fun updateEntries(newEntries: List<WorkoutEntry>) {
        this.entries = newEntries
        notifyDataSetChanged() // maybe we should use DiffUtil for more efficient updates
    }
}