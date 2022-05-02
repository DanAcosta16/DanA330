package com.example.worddictionary.definition

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.worddictionary.database.Word
import com.example.worddictionary.database.WordDatabaseDao
import java.lang.IllegalArgumentException

class DefinitionViewModelFactory(val word: Word, private val database: WordDatabaseDao): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(DefinitionViewModel::class.java)){
            return DefinitionViewModel(word, database) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}