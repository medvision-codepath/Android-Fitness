package com.example.gymapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.gymapp.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]

        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        val manager = GridLayoutManager(activity, 4)
        binding.workoutTypeList.layoutManager = manager

        val adapter = HomeAdapter(WorkoutDayListener { workoutDay ->
            homeViewModel.onWorkoutClicked(workoutDay)
        })
        binding.workoutTypeList.adapter = adapter

        homeViewModel.navigateToWorkout.observe(viewLifecycleOwner, Observer { workoutDay ->
            workoutDay?.let {
                this.findNavController().navigate(
                    HomeFragmentDirections
                        .actionNavigationHomeToNavigationWorkout(workoutDay.workoutType, workoutDay.weekCount)
                )
                homeViewModel.doneNavigating()
            }
        })

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}