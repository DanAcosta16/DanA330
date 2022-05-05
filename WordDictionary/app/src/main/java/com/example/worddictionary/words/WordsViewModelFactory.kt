package com.example.worddictionary.words

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.worddictionary.database.WordDatabaseDao
import java.lang.IllegalArgumentException

class WordsViewModelFactory(private val database: WordDatabaseDao): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(WordsViewModel::class.java)){
            return WordsViewModel(database) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}