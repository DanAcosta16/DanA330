package com.example.worddictionary.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.worddictionary.database.Word

@Dao
interface WordDatabaseDao {

    @Insert
    suspend fun insertWord(word: Word)

    @Update
    suspend fun updateWord(word: Word)

    @Query("SELECT exists(select * from dictionary_word WHERE wordId = :id)")
    suspend fun wordExists(id: String): Boolean

    @Query("SELECT * FROM dictionary_word ORDER BY wordId DESC")
    fun getAllWords(): LiveData<List<Word>>

    @Query("SELECT * FROM dictionary_word where active = 1 ORDER BY wordId DESC")
    fun getActiveWords(): LiveData<List<Word>>

    @Query("SELECT * FROM dictionary_word where active = 0 ORDER BY wordId DESC")
    fun getInactiveWords(): LiveData<List<Word>>
}