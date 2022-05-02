package com.example.worddictionary.words

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.worddictionary.database.Word
import com.example.worddictionary.database.WordDatabaseDao
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class WordsViewModel(db: WordDatabaseDao): ViewModel() {
    val database: WordDatabaseDao = db
    fun dbExists() = runBlocking {
        val addData = database.getAllWords()
        return@runBlocking addData.value?.size!! > 0
    }


    var words: LiveData<List<Word>>? = null

    init {
        getWords()
    }

    private fun getWords(){
        viewModelScope.launch {
            words = try {
                database.getActiveWords()
            } catch (e: Exception) {
                null
            }
        }
    }
}