package com.example.android_fitness.app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.android_fitness.R

class BodyTrackerFragment : Fragment() {

    private lateinit var weightInput: EditText
    private lateinit var bodyFatInput: EditText
    private lateinit var submitButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_body_tracker, container, false)

        weightInput = view.findViewById(R.id.weightInput)
        bodyFatInput = view.findViewById(R.id.bodyFatInput)
        submitButton = view.findViewById(R.id.submitButton)

        submitButton.setOnClickListener {
            val weight = weightInput.text.toString().trim()
            val bodyFat = bodyFatInput.text.toString().trim()

            if (weight.isNotEmpty() && bodyFat.isNotEmpty()) {
                // Process the input values here, such as saving to a database or sending to another system
                Toast.makeText(activity, "Data Submitted: Weight - $weight kg, Body Fat - $bodyFat%", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(activity, "Please fill in all fields", Toast.LENGTH_LONG).show()
            }
        }

        return view
    }
}
