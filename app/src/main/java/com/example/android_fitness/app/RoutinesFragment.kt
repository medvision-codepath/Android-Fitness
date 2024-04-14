package com.example.android_fitness.app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.example.android_fitness.R

class RoutinesFragment : Fragment() {

    private lateinit var routinesRecyclerView: RecyclerView
    private lateinit var addRoutineButton: FloatingActionButton

    // Dummy data and adapter setup would go here
    private var routinesList = mutableListOf<String>()
    private lateinit var adapter: RoutinesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_routines, container, false)

        routinesRecyclerView = view.findViewById(R.id.rvRoutines)
        addRoutineButton = view.findViewById(R.id.addRoutineButton)

        // Initialize the RecyclerView
        routinesList = mutableListOf("Morning Exercise", "Study Session", "Work on Project")
        adapter = RoutinesAdapter(routinesList)
        routinesRecyclerView.adapter = adapter
        routinesRecyclerView.layoutManager = LinearLayoutManager(context)

        addRoutineButton.setOnClickListener {
            // Handle adding a new routine
            addNewRoutine()
        }

        return view
    }

    private fun addNewRoutine() {
        // Dummy function to add a routine
        routinesList.add("New Routine")
        adapter.notifyItemInserted(routinesList.size - 1)
    }
}

// Placeholder adapter for RecyclerView
class RoutinesAdapter(private val items: List<String>) : RecyclerView.Adapter<RoutinesAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        // View holder contents would be defined here
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(android.R.layout.simple_list_item_1, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // Bind data to the view
    }

    override fun getItemCount() = items.size
}
