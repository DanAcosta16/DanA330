package com.example.worddictionary.words


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.worddictionary.database.Word
import com.example.worddictionary.database.WordDatabaseDao


class WordsViewModel(db: WordDatabaseDao): ViewModel() {
    private val dao: WordDatabaseDao = db
    private var _currentFilter = MutableLiveData<WordsFilter>()
    val currentFilter: LiveData<WordsFilter>
        get() = _currentFilter

    var dictWords: LiveData<List<Word>>

    init {
        dictWords = dao.getActiveWords()
        _currentFilter.value = WordsFilter.SHOW_ACTIVE
    }

    fun changeFilter(filter: WordsFilter) {
        dictWords = when (filter) {
            WordsFilter.SHOW_ALL -> dao.getAllWords()
            WordsFilter.SHOW_ACTIVE -> dao.getActiveWords()
            else -> dao.getInactiveWords()
        }
        _currentFilter.value = filter
    }

    suspend fun clearData() {
        dao.clear()
    }

    suspend fun updateWord(word: Word) {
        dao.updateWord(word)
    }



}

enum class WordsFilter {
    SHOW_ALL,
    SHOW_ACTIVE,
    SHOW_INACTIVE
}