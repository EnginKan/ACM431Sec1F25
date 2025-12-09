package com.yeditepe.acm43project.week11.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [WordEntity::class], version = 1)
abstract class WordDatabase: RoomDatabase() {
    abstract fun wordDAO(): WordDAO

    companion object{
        //singleton object
        @Volatile
        private var INSTANCE: WordDatabase? = null
        //we need a method that return INSTANCE such that it return
        //the same instance if it exist otherwise, fşrst initilize then return it

        fun getInstance(context: Context): WordDatabase{
            return INSTANCE?:synchronized(this){
                INSTANCE?: Room.databaseBuilder(context,
                    WordDatabase::class.java,
                    "words_db")
                    .fallbackToDestructiveMigration(true)
                    .build()
                    .also { INSTANCE = it }

            }
        }

    }
}