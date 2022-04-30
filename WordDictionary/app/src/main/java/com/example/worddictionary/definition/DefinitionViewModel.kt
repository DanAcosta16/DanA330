package com.example.worddictionary.definition

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.worddictionary.words.Word

class DefinitionViewModel(word: Word) : ViewModel() {
    val myWord: Word = word


}