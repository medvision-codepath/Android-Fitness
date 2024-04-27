package com.example.gymapp.ui.history

import androidx.lifecycle.*
import com.example.gymapp.database.Set
import com.example.gymapp.database.SetDatabaseDao
import java.util.*

class HistoryViewModel(setDatabase: SetDatabaseDao) : ViewModel() {

    private val sets = setDatabase.getSetOrderedBySetId()

    var sessions: LiveData<List<Session>?> = sets.map {
        sets.value?.let { sets -> mapSetsToSessions(sets) }
    }

    private fun mapSetsToSessions(sets: List<Set>): List<Session> {
        val mapSessionIdToSets = groupSetsBySessionId(sets)

        val sessions = mutableListOf<Session>()
        mapSessionIdToSets.forEach{entry ->
            val firstSet = entry.value[0]
            val date = firstSet.date
            val workoutType = firstSet.workoutType
            val weekCount = firstSet.weekCount
            val trainingMax = firstSet.trainingMax
            val setsInSession = entry.value.sortedBy { set -> set.weight }

            sessions.add(Session(date, workoutType, weekCount, trainingMax, setsInSession))
        }
        return sessions
    }

    private fun groupSetsBySessionId(sets: List<Set>): Map<UUID, List<Set>> {
        return sets.groupBy { set -> set.sessionId }
    }

}