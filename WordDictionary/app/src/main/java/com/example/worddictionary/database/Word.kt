package com.example.worddictionary.database

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "dictionary_word")
@Parcelize
data class Word (
    @PrimaryKey
    val wordId: String,
    val image: String? = null,
    val defOne: String,
    val defTwo: String? = null,
    val defThree: String? = null,
    var active: Boolean = true

): Parcelable