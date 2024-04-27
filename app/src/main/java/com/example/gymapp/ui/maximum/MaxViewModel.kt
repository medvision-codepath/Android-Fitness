package com.example.gymapp.ui.maximum

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gymapp.database.TrainingMax
import com.example.gymapp.database.TrainingMaxDatabaseDao
import kotlinx.coroutines.*

class MaxViewModel(
    val database: TrainingMaxDatabaseDao
) : ViewModel() {

    private var viewmodelJob = Job()

    private val uiScope = CoroutineScope(Dispatchers.Main + viewmodelJob)

    var squatMax = MutableLiveData<Float>()

    var benchMax = MutableLiveData<Float>()

    var deadliftMax = MutableLiveData<Float>()

    var ohpMax = MutableLiveData<Float>()

    var editSquatMax = MutableLiveData<Float>()

    var editBenchMax = MutableLiveData<Float>()

    var editDeadliftMax = MutableLiveData<Float>()

    var editOhpMax = MutableLiveData<Float>()

    init {
        initialiseLatestTrainingMaxes()
    }

    private fun initialiseLatestTrainingMaxes() {
        uiScope.launch {
            val latestTrainingMax = getLatestTrainingMaxesFromDatabase()
            if (latestTrainingMax != null) {
                squatMax.value = latestTrainingMax.squatMax
                benchMax.value = latestTrainingMax.benchMax
                deadliftMax.value = latestTrainingMax.deadliftMax
                ohpMax.value = latestTrainingMax.ohpMax
            }
        }
    }

    private suspend fun getLatestTrainingMaxesFromDatabase(): TrainingMax? {
        return withContext(Dispatchers.IO) {
            database.getLatestTrainingMax()
        }
    }

    fun onSaveMaxes(trainingMax: TrainingMax) {
        squatMax.value = trainingMax.squatMax
        benchMax.value = trainingMax.benchMax
        deadliftMax.value = trainingMax.deadliftMax
        ohpMax.value = trainingMax.ohpMax

        viewModelScope.launch {
            update(trainingMax)
        }
    }

    private suspend fun update(trainingMax: TrainingMax) {
        withContext(Dispatchers.IO) {
            database.upsertTrainingMax(trainingMax)
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewmodelJob.cancel()
    }
}