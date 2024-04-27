package com.example.gymapp.ui.maximum

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.gymapp.R
import com.example.gymapp.database.TrainingMaxDatabase
import com.example.gymapp.databinding.FragmentMaxBinding

class MaxFragment : Fragment() {

    private var _binding: FragmentMaxBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        val application = requireNotNull(this.activity).application

        val dataSource = TrainingMaxDatabase.getInstance(application).trainingMaxDatabaseDao

        val viewModelFactory = MaxViewModelFactory(dataSource)

        val maxViewModel = ViewModelProvider(this, viewModelFactory)[MaxViewModel::class.java]

        _binding = FragmentMaxBinding.inflate(inflater, container, false)

        maxViewModel.squatMax.observe(viewLifecycleOwner) {
            it?.let {
                binding.squatMaxValue.text = getString(R.string.weight_format_text, it)
            }
        }

        maxViewModel.benchMax.observe(viewLifecycleOwner) {
            it?.let {
                binding.benchMaxValue.text = getString(R.string.weight_format_text, it)
            }
        }

        maxViewModel.deadliftMax.observe(viewLifecycleOwner) {
            it?.let {
                binding.deadliftMaxValue.text = getString(R.string.weight_format_text, it)
            }
        }

        maxViewModel.ohpMax.observe(viewLifecycleOwner) {
            it?.let {
                binding.ohpMaxValue.text = getString(R.string.weight_format_text, it)
            }
        }

        val fab: View = binding.fab
        fab.setOnClickListener {
            val args = Bundle()
            maxViewModel.squatMax.value?.let { args.putFloat("squatMax", it) }
            maxViewModel.benchMax.value?.let { args.putFloat("benchMax", it) }
            maxViewModel.deadliftMax.value?.let { args.putFloat("deadliftMax", it) }
            maxViewModel.ohpMax.value?.let { args.putFloat("ohpMax", it) }

            MaxDialogFragment()
                .show(childFragmentManager, MaxDialogFragment.TAG)
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}