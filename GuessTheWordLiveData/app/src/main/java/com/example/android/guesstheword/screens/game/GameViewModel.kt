/*
 * Copyright (C) 2019 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.guesstheword.screens.game

import android.os.CountDownTimer
import android.text.format.DateUtils
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.android.guesstheword.screens.score.ScoreFragmentArgs


class GameViewModel(timerValue: Int) : ViewModel() {

    // The current _word
    private val _word = MutableLiveData<String>()

    // The current score
    private val _score = MutableLiveData<Int>()
    val score: LiveData<Int>
        get() = _score

    val word: LiveData<String>
        get() = _word
    val time = timerValue * 1000

    private val _eventGameFinish = MutableLiveData<Boolean>()
    val eventGameFinish: LiveData<Boolean>
        get() = _eventGameFinish


    // The list of words - the front of the list is the next _word to guess
    private lateinit var wordList: MutableList<String>


    /**
     * Resets the list of words and randomizes the order
     */
    private fun resetList() {
        wordList = mutableListOf(
            "queen",
            "hospital",
            "basketball",
            "cat",
            "change",
            "snail",
            "soup",
            "calendar",
            "sad",
            "desk",
            "guitar",
            "home",
            "railway",
            "zebra",
            "jelly",
            "car",
            "crow",
            "trade",
            "bag",
            "roll",
            "bubble"
        )
        wordList.shuffle()
    }



    init {
        _word.value = ""
        _score.value = 0
        Log.i("GameViewModel", "GameViewModel created!")
        resetList()
        nextWord()
        // Creates a timer which triggers the end of the game when it finishes
    }

    /**
     * Callback called when the ViewModel is destroyed
     */
    override fun onCleared() {
        super.onCleared()
        // Cancel the timer
        //timer.cancel()
    }

    /** Methods for updating the UI **/
    fun onSkip() {
        _score.value = (_score.value)?.minus(1)
        nextWord()
    }

    fun onCorrect() {
        _score.value = (_score.value)?.plus(1)
        nextWord()
    }

    fun startTimer(){
            val timer: CountDownTimer
            timer = object : CountDownTimer(time.toLong(), ONE_SECOND) {

                override fun onTick(millisUntilFinished: Long) {
                    _currentTime.value = millisUntilFinished / ONE_SECOND
                }

                override fun onFinish() {
                    _currentTime.value = DONE
                    if(time != -1000)
                        onGameFinish()
                }
            }

            if(time != -1)
                timer.start()
            else {
                timer.cancel()
            }

    }

    /**
     * Moves to the next _word in the list.
     */
    private fun nextWord() {
        // Shuffle the word list, if the list is empty
        if (wordList.isEmpty()) {
            resetList()
        } else {
            // Remove a word from the list
            _word.value = wordList.removeAt(0)
        }
    }

    /** Method for the game completed event **/

    fun onGameFinish() {
        _eventGameFinish.value = true
    }

    fun onGameFinishComplete() {
        _eventGameFinish.value = false
    }

    companion object {

        // Time when the game is over
        private const val DONE = 0L

        // Countdown time interval
        private const val ONE_SECOND = 1000L
        // Total time for the game

        private const val COUNTDOWN_TIME = 60000L

    }

    // Countdown time
    private val _currentTime = MutableLiveData<Long>()
    val currentTime: LiveData<Long>
        get() = _currentTime

    // The String version of the current time
    val currentTimeString = Transformations.map(currentTime) { time ->
        DateUtils.formatElapsedTime(time)
    }

}
