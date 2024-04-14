package com.example.android_fitness.app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android_fitness.R
import com.example.android_fitness.local.database.WorkoutEntry
import java.text.SimpleDateFormat
import java.util.*

class MainFragment : Fragment() {

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)

        setupUI(view)
    }

    private fun setupUI(view: View) {
        val recyclerView: RecyclerView = view.findViewById(R.id.my_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        val adapter = WorkoutEntryAdapter(emptyList())
        recyclerView.adapter = adapter

        viewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
        viewModel.allEntries.observe(viewLifecycleOwner) { entries ->

            if (entries.isNotEmpty()) {
                adapter.updateEntries(listOf(entries.last()))
            } else {
                adapter.updateEntries(emptyList())
            }
        }

        val editTextWorkoutEntry: EditText = view.findViewById(R.id.editTextWorkoutEntry)
        val buttonSubmit: Button = view.findViewById(R.id.buttonSubmit)
        buttonSubmit.setOnClickListener {
            val workoutEntry = editTextWorkoutEntry.text.toString().toDoubleOrNull()
            if (workoutEntry != null) {
                val newEntry = WorkoutEntry(System.currentTimeMillis().toInt(), getCurrentDate(), workoutEntry)
                viewModel.insertEntry(newEntry)
                editTextWorkoutEntry.text.clear()
                Toast.makeText(requireContext(), "Entry added: $workoutEntry", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(), "Invalid input", Toast. LENGTH_SHORT).show()
            }
        }
    }

    private fun getCurrentDate(): String {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return dateFormat.format(Date())
    }
}