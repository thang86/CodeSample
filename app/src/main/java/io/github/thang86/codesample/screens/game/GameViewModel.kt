package io.github.thang86.codesample.screens.game

import android.util.Log
import androidx.lifecycle.ViewModel

/**
 *
 * Created by Thang86
 */
class GameViewModel : ViewModel() {
    init {
        resetList()
        nextWord()
        Log.d("GameViewModel", "GameViewModel created!")
    }

    override fun onCleared() {
        super.onCleared()
        Log.d("GameViewModel", "GameViewModel onCleared!")
    }

    // The current word
    var word = ""

    // The current score
    var score = 0

    // The list of words - the front of the list is the next word to guess
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

    /** Methods for buttons presses **/

    public fun onSkip() {

        score--
        nextWord()
    }

    public fun onCorrect() {
        score++
        nextWord()
    }

    /**
     * Moves to the next word in the list
     */
    fun nextWord() {
        if (!wordList.isEmpty()) {
            //Select and remove a word from the list
            word = wordList.removeAt(0)
        }
    }


}