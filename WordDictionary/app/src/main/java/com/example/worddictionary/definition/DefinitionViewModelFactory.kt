package com.example.worddictionary.definition

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.worddictionary.words.Word
import java.lang.IllegalArgumentException

class DefinitionViewModelFactory(val word: Word): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(DefinitionViewModel::class.java)){
            return DefinitionViewModel(word) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}