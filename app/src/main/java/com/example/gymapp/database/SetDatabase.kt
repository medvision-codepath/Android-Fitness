package com.example.gymapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [Set::class], version = 1)
@TypeConverters(Converters::class)
abstract class SetDatabase : RoomDatabase() {

     abstract val setDatabaseDao: SetDatabaseDao

     companion object {

         @Volatile
         private var INSTANCE: SetDatabase? = null

         fun getInstance(context: Context): SetDatabase {
             synchronized(this) {
                 var instance = INSTANCE

                 if (instance == null) {
                     instance = Room.databaseBuilder(
                         context.applicationContext,
                         SetDatabase::class.java,
                         "set_database"
                     )
                        .fallbackToDestructiveMigration()
                        .build()

                     INSTANCE = instance
                 }
                 return instance
             }
         }
     }
}