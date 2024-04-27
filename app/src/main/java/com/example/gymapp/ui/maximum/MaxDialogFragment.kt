package com.example.gymapp.ui.maximum

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.example.gymapp.database.TrainingMax
import com.example.gymapp.databinding.FragmentMaxDialogBinding

class MaxDialogFragment : DialogFragment() {

    private val maxViewModel: MaxViewModel by viewModels(ownerProducer = { requireParentFragment() })

    private var _binding: FragmentMaxDialogBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentMaxDialogBinding.inflate(inflater, container, false)

        maxViewModel.editSquatMax.value = maxViewModel.squatMax.value
        maxViewModel.editBenchMax.value = maxViewModel.benchMax.value
        maxViewModel.editDeadliftMax.value = maxViewModel.deadliftMax.value
        maxViewModel.editOhpMax.value = maxViewModel.ohpMax.value

        maxViewModel.editSquatMax.observe(viewLifecycleOwner) {
            it?.let {
                binding.editSquatValue.setText(it.toString())
            }
        }

        maxViewModel.editBenchMax.observe(viewLifecycleOwner) {
            it?.let {
                binding.editBenchValue.setText(it.toString())
            }
        }

        maxViewModel.editDeadliftMax.observe(viewLifecycleOwner) {
            it?.let {
                binding.editDeadliftValue.setText(it.toString())
            }
        }

        maxViewModel.editOhpMax.observe(viewLifecycleOwner) {
            it?.let {
                binding.editOhpValue.setText(it.toString())
            }
        }

        binding.saveMaxesButton.setOnClickListener{
            onSaveMaxes()
            dismiss()
        }

        binding.autoIncrementButton.setOnClickListener{
            onAutoIncrementMaxes()
        }

        return binding.root
    }

    private fun onSaveMaxes() {
        val trainingMaxes = TrainingMax(
            squatMax = binding.editSquatValue.text.toString().toFloatOrNull() ?: 0f,
            benchMax = binding.editBenchValue.text.toString().toFloatOrNull() ?: 0f,
            deadliftMax = binding.editDeadliftValue.text.toString().toFloatOrNull() ?: 0f,
            ohpMax = binding.editOhpValue.text.toString().toFloatOrNull() ?: 0f,
        )

        maxViewModel.onSaveMaxes(trainingMaxes)
    }

    private fun onAutoIncrementMaxes() {
        val upperBodyIncrement = 2.5f
        val lowerBodyIncrement = 5f

        maxViewModel.editSquatMax.value = maxViewModel.editSquatMax.value?.plus(lowerBodyIncrement)
        maxViewModel.editBenchMax.value = maxViewModel.editBenchMax.value?.plus(upperBodyIncrement)
        maxViewModel.editDeadliftMax.value = maxViewModel.editDeadliftMax.value?.plus(lowerBodyIncrement)
        maxViewModel.editOhpMax.value = maxViewModel.editOhpMax.value?.plus(upperBodyIncrement)
    }

    companion object {
        const val TAG = "MaxDialogFragment"
    }
}