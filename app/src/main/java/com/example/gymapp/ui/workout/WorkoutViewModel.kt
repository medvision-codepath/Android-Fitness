package com.example.gymapp.ui.workout

import androidx.lifecycle.*
import com.example.gymapp.database.SetDatabaseDao
import com.example.gymapp.database.TrainingMax
import com.example.gymapp.database.TrainingMaxDatabaseDao
import com.example.gymapp.ui.home.WorkoutType
import com.example.gymapp.util.Utils.roundDownToNearestIncrement
import kotlinx.coroutines.*
import java.time.LocalDateTime
import java.util.*

class WorkoutViewModel(
    private val trainingMaxDatabase: TrainingMaxDatabaseDao,
    private val setDatabase: SetDatabaseDao,
    val workoutType: WorkoutType,
    val weekCount: Int
) : ViewModel() {

    private var viewmodelJob = Job()

    private val uiScope = CoroutineScope(Dispatchers.Main + viewmodelJob)

    private var trainingMax = MutableLiveData<TrainingMax?>()

    val setList : LiveData<List<WorkoutSet>> = trainingMax.map {
        getWorkoutSetList(getMaxForType(workoutType), weekCount)
    }

    private val _navigateToHome = MutableLiveData<Boolean>()

    val navigateToHome: LiveData<Boolean?>
        get() = _navigateToHome

    init {
        initialiseLatestTrainingMaxes()
    }

    private fun initialiseLatestTrainingMaxes() {
        uiScope.launch {
            val latestTrainingMax = getLatestTrainingMaxesFromDatabase()
            if (latestTrainingMax != null) {
                trainingMax.value = latestTrainingMax
            }
        }
    }

    private suspend fun getLatestTrainingMaxesFromDatabase(): TrainingMax? {
        return withContext(Dispatchers.IO) {
            trainingMaxDatabase.getLatestTrainingMax()
        }
    }

    fun getMaxForType(workoutType: WorkoutType): Float? {
        return when (workoutType) {
            WorkoutType.SQUAT -> trainingMax.value?.squatMax
            WorkoutType.BENCH -> trainingMax.value?.benchMax
            WorkoutType.DEADLIFT -> trainingMax.value?.deadliftMax
            WorkoutType.OHP -> trainingMax.value?.ohpMax
        }
    }

    private fun getWorkoutSetList(maximum: Float?, weekCount: Int): List<WorkoutSet> {
        val workoutSets = mutableListOf<WorkoutSet>()

        if (maximum != null) {
            workoutSets.addAll(getWarmUpWorkoutSets(maximum, weekCount))
            workoutSets.addAll(getMainWorkoutSets(maximum, weekCount))
            workoutSets.addAll(getBBBWorkoutSets(maximum, weekCount))
        }
        return workoutSets
    }

    private fun getWarmUpWorkoutSets(max: Float, weekCount: Int): List<WorkoutSet> {
        val sets = mutableListOf<WorkoutSet>()
        val warmUpSetMultipliers = listOf(0.4f, 0.5f, 0.6f)

        for (i in 0..2) {
            sets.add(WorkoutSet(WorkoutSetType.WARM_UP, i + 1, warmUpSetMultipliers[i] * max, 5, weekCount))
        }
        return sets
    }

    private val mapWeekToMultiplierSets = mapOf(
        1 to listOf(0.65f, 0.75f, 0.85f),
        2 to listOf(0.7f, 0.8f, 0.9f),
        3 to listOf(0.75f, 0.85f, 0.95f),
        4 to listOf(0.4f, 0.5f, 0.6f)
    )
    private val mapWeekToReps = mapOf(
        1 to listOf(5, 5, 5),
        2 to listOf(3, 3, 3),
        3 to listOf(5, 3, 1),
        4 to listOf(5, 5, 5)
    )

    private fun getMainWorkoutSets(max: Float, weekCount: Int): List<WorkoutSet> {
        val sets = mutableListOf<WorkoutSet>()
        val mainSetMultipliers = mapWeekToMultiplierSets[weekCount]!!
        val mainSetReps = mapWeekToReps[weekCount]!!

        for (i in 0..2) {
            sets.add(WorkoutSet(WorkoutSetType.MAIN, i + 4, mainSetMultipliers[i] * max, mainSetReps[i], weekCount))
        }
        return sets
    }

    fun getMainSetWeights(): List<Float> {
        val max = getMaxForType(workoutType)?: 0f

        val weights = mutableListOf<Float>()
        for (i in 0..2) {
            val mainSetMultipliers = mapWeekToMultiplierSets[weekCount]!!
            weights.add(roundDownToNearestIncrement(mainSetMultipliers[i] * max, 2.5f))
        }
        return weights
    }

    fun getMainSetReps(): List<Int> {
        return mapWeekToReps[weekCount]!!
    }

    private fun getBBBWorkoutSets(max: Float, weekCount: Int): List<WorkoutSet> {
        val sets = mutableListOf<WorkoutSet>()
        val bbbSetMultiplier = 0.5f

        for (i in 0..4) {
            sets.add(WorkoutSet(WorkoutSetType.BBB, i + 7, bbbSetMultiplier * max, 10, weekCount))
        }
        return sets
    }

    fun onSaveSet(weight: Float, repCount: Int, max: Float, sessionId: UUID) {
        val set = com.example.gymapp.database.Set(
            date = LocalDateTime.now(),
            workoutType = workoutType,
            setType = WorkoutSetType.MAIN,
            weekCount = weekCount,
            trainingMax = max,
            weight = weight,
            repCount = repCount,
            sessionId = sessionId
        )

        uiScope.launch {
            update(set)
        }
    }

    private suspend fun update(set: com.example.gymapp.database.Set) {
        withContext(Dispatchers.IO) {
            setDatabase.upsertSet(set)
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewmodelJob.cancel()
    }

    fun onWorkoutComplete() {
        _navigateToHome.value = true
    }

    fun doneNavigating() {
        _navigateToHome.value = false
    }
}