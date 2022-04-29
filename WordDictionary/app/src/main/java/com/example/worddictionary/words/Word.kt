package com.example.worddictionary.words

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Word (
    val wordId: String,
    val image: String? = null,
    val defOne: String,
    val defTwo: String? = null,
    val defThree: String? = null

): Parcelable