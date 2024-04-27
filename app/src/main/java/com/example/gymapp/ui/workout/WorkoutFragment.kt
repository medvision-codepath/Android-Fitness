package com.example.gymapp.ui.workout

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.NumberPicker
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.gymapp.database.SetDatabase
import com.example.gymapp.database.TrainingMaxDatabase
import com.example.gymapp.databinding.FragmentWorkoutBinding
import com.example.gymapp.util.Utils


class WorkoutFragment: Fragment(){

    private var _binding: FragmentWorkoutBinding? = null

    private val binding get() = _binding!!

    private var amrapRepCount: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        val application = requireNotNull(this.activity).application

        val trainingMaxDataSource = TrainingMaxDatabase.getInstance(application).trainingMaxDatabaseDao

        val setDataSource = SetDatabase.getInstance(application).setDatabaseDao

        val args: WorkoutFragmentArgs by navArgs()

        val viewModelFactory = WorkoutViewModelFactory(trainingMaxDataSource, setDataSource, args.workoutType, args.weekCount)

        val workoutViewModel = ViewModelProvider(this, viewModelFactory)[WorkoutViewModel::class.java]

        _binding = FragmentWorkoutBinding.inflate(inflater, container, false)

        binding.setList.adapter = WorkoutAdapter(emptyList(), args.weekCount)

        binding.workoutInfo.text = Utils.formatWorkoutInfo(args.workoutType, args.weekCount)

        workoutViewModel.setList.observe(viewLifecycleOwner) {
            binding.setList.adapter = workoutViewModel.setList.value?.let { setList ->
                WorkoutAdapter(setList, args.weekCount)
            }!!
        }

        val recordAmrapButton: View = binding.recordAmrapButton
        if (args.weekCount == 4) {
            recordAmrapButton.isEnabled = false
        }

        recordAmrapButton.setOnClickListener {
            val numberPicker = NumberPicker(requireActivity())
            numberPicker.minValue = 0
            numberPicker.maxValue = 30
            numberPicker.value = when (args.weekCount) {
                1 -> 5
                2 -> 3
                3 -> 1
                else -> 5
            }
            numberPicker.wrapSelectorWheel = true

            val dialog = AlertDialog.Builder(requireContext())
                .setTitle("Reps completed for AMRAP set")
                .setView(numberPicker)
                .setPositiveButton("OK") { _, _ ->
                    amrapRepCount = numberPicker.value
                }
                .setNegativeButton("Cancel", null)
                .create()

            dialog.show()
        }

        val finishWorkoutButton: View = binding.finishWorkoutButton
        finishWorkoutButton.setOnClickListener {
            WorkoutFinishDialogFragment(amrapRepCount)
                .show(childFragmentManager, WorkoutFinishDialogFragment.TAG)
        }

        workoutViewModel.navigateToHome.observe(viewLifecycleOwner) {
            if (it == true) {
                this.findNavController().navigate(
                    WorkoutFragmentDirections.actionNavigationWorkoutToNavigationHome()
                )
                workoutViewModel.doneNavigating()
            }
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}