package com.example.android.guesstheword.screens.game

import android.util.Log
import androidx.lifecycle.ViewModel

class GameViewModel (): ViewModel() {
    var word: String = ""
    var score: Int = 0
    lateinit var wordList: MutableList<String>

    init {
        resetList()
        nextWord()


        Log.i(TAG, "GameViewModel is created")

    }

    override fun onCleared() {
        super.onCleared()

        Log.i(TAG, "GameViewModel is destroy")
    }

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

    private fun nextWord() {
        if (!wordList.isEmpty()) {
            //Select and remove a word from the list
            word = wordList.removeAt(0)
        }
    }

    fun onSkip() {
        score--
        nextWord()
    }

    fun onCorrect() {
        score++
        nextWord()
    }

    companion object {
        const val TAG = "GameViewModel"
    }
}