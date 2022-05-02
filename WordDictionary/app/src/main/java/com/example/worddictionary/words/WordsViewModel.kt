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
    private val _dictWords = db.getActiveWords()

    val dictWords: LiveData<List<Word>>
        get() = _dictWords

    fun changeFilter(filter: String) {
    }
}