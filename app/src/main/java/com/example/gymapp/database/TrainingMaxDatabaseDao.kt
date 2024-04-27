package com.example.gymapp.database

import androidx.room.*

@Dao
interface TrainingMaxDatabaseDao {

    @Insert
    suspend fun insertTrainingMax(trainingMax: TrainingMax)

    @Upsert
    suspend fun upsertTrainingMax(trainingMax: TrainingMax)

    @Delete
    suspend fun deleteTrainingMax(trainingMax: TrainingMax)

    @Query("SELECT * FROM table_training_max ORDER BY id DESC LIMIT 1")
    suspend fun getLatestTrainingMax(): TrainingMax?

}