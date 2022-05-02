package com.example.worddictionary.definition

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.worddictionary.database.Word
import com.example.worddictionary.database.WordDatabaseDao
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DefinitionViewModel(word: Word, database: WordDatabaseDao) : ViewModel() {
    val myWord: Word = word
    private val db: WordDatabaseDao = database
    fun addWordToDatabase(){
        viewModelScope.launch {
            db.insertWord(myWord)
        }

    }

    suspend fun alreadyAdded(): Boolean = withContext(viewModelScope.launch{}) {
        return@withContext db.wordExists(myWord.wordId)
    }
}


