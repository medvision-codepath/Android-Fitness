package com.example.gymapp.ui.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.gymapp.database.SetDatabase
import com.example.gymapp.databinding.FragmentHistoryBinding

class HistoryFragment : Fragment() {

    private var _binding: FragmentHistoryBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val application = requireNotNull(this.activity).application

        val setDataSource = SetDatabase.getInstance(application).setDatabaseDao

        val viewModelFactory = HistoryViewModelFactory(setDataSource)

        val historyViewModel = ViewModelProvider(this, viewModelFactory)[HistoryViewModel::class.java]

        _binding = FragmentHistoryBinding.inflate(inflater, container, false)

        binding.sessionHistoryList.adapter = HistoryAdapter(emptyList())

        historyViewModel.sessions.observe(viewLifecycleOwner) {
            binding.sessionHistoryList.adapter = it?.let { sessionList -> HistoryAdapter(sessionList) }!!
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}