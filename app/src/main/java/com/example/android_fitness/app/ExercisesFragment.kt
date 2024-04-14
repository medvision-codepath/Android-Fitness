package com.example.android_fitness.app

import android.os.Bundle
import android.os.Handler
import android.os.SystemClock
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.android_fitness.R

class ExerciseFragment : Fragment() {

    private lateinit var timerText: TextView
    private lateinit var startButton: Button
    private lateinit var pauseButton: Button
    private lateinit var resetButton: Button

    private var startTime = 0L
    private var timeInMilliseconds = 0L
    private var timeBuffer = 0L
    private var updatedTime = 0L
    private var handler: Handler = Handler()
    private var running = false

    private val runnable = object : Runnable {
        override fun run() {
            if (running) {
                timeInMilliseconds = SystemClock.uptimeMillis() - startTime
                updatedTime = timeBuffer + timeInMilliseconds

                val seconds = (updatedTime / 1000).toInt()
                val minutes = seconds / 60
                val hours = minutes / 60

                val displayTime = String.format("%02d:%02d:%02d", hours, minutes % 60, seconds % 60)
                timerText.text = displayTime

                handler.postDelayed(this, 1000)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_exercises, container, false)

        timerText = view.findViewById(R.id.timerText)
        startButton = view.findViewById(R.id.startButton)
        pauseButton = view.findViewById(R.id.pauseButton)
        resetButton = view.findViewById(R.id.resetButton)

        startButton.setOnClickListener {
            if (!running) {
                startTime = SystemClock.uptimeMillis()
                handler.postDelayed(runnable, 0)
                running = true
            }
        }

        pauseButton.setOnClickListener {
            if (running) {
                timeBuffer += timeInMilliseconds
                handler.removeCallbacks(runnable)
                running = false
            }
        }

        resetButton.setOnClickListener {
            startTime = 0L
            timeInMilliseconds = 0L
            timeBuffer = 0L
            updatedTime = 0L
            timerText.text = "00:00:00"
            handler.removeCallbacks(runnable)
            running = false
        }

        return view
    }
}