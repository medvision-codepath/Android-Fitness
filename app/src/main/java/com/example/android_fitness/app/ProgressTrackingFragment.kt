package com.example.android_fitness.app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.android_fitness.R

class ProgressTrackingFragment : Fragment() {

    private lateinit var progressBar: ProgressBar
    private lateinit var progressText: TextView
    private lateinit var incrementButton: Button
    private lateinit var resetButton: Button

    private var progress = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_progress_tracking, container, false)

        progressBar = view.findViewById(R.id.progressBar)
        progressText = view.findViewById(R.id.progressText)
        incrementButton = view.findViewById(R.id.incrementButton)
        resetButton = view.findViewById(R.id.resetButton)

        incrementButton.setOnClickListener {
            if (progress < 100) {
                progress += 10  // Increment progress by 10%
                progressBar.progress = progress
                progressText.text = "Progress: $progress%"
            }
        }

        resetButton.setOnClickListener {
            progress = 0
            progressBar.progress = progress
            progressText.text = "Progress: $progress%"
        }

        return view
    }
}
