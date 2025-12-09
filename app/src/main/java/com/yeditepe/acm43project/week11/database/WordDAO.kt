package com.yeditepe.acm43project.week11.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update


@Dao
interface WordDAO {

    @Query("SELECT * FROM words")
    suspend fun getAllWords(): List<WordEntity>

    @Insert
    suspend fun insert(word: WordEntity): WordEntity

    @Insert
    suspend fun insert(vararg words: WordEntity ): Unit

    @Delete
    suspend fun delete(word: WordEntity): Unit

    @Delete
    suspend fun delete(vararg words: WordEntity): WordEntity

    @Update
    suspend fun update(word: WordEntity): Unit

    @Update
    suspend fun update(vararg words: WordEntity): Unit

    @Query("UPDATE words SET word= :word WHERE _id= :id")
    suspend fun update(id: Int, word: String): Unit

    @Query("SELECT * FROM words where _id= :id")
    suspend fun getWordEntityByID(id: Int): WordEntity


}