package io.github.thang86.codesample.screens.game

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 *
 * Created by Thang86
 */
class GameViewModel : ViewModel() {

    // The current word
    private val _word: MutableLiveData<String> = MutableLiveData("")

    // The current score
    private val _score: MutableLiveData<Int> = MutableLiveData(0)

    val word: LiveData<String> = _word
    val score: LiveData<Int> = _score

    // The list of words - the front of the list is the next word to guess
    private lateinit var wordList: MutableList<String>

    private val _eventGameFinish = MutableLiveData<Boolean>()
    val eventGameFinish: LiveData<Boolean>
        get() = _eventGameFinish

    init {
        resetList()
        nextWord()
        Log.d("GameViewModel", "GameViewModel created!")
    }


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

    fun onSkip() {

        _score.value = (_score.value ?: 0).minus(1)
        nextWord()
    }

    fun onCorrect() {
        _score.value = (_score.value ?: 0).plus(1)
        nextWord()
    }

    /**
     * Moves to the next word in the list
     */
    private fun nextWord() {
        if (wordList.isEmpty()) {
            onGameFinish()

        } else {
            //Select and remove a word from the list
            _word.value = wordList.removeAt(0)
        }
    }

    /** Method for the game completed event **/
    override fun onCleared() {
        super.onCleared()
        Log.d("GameViewModel", "GameViewModel onCleared!")
    }

    private fun onGameFinish() {
        _eventGameFinish.value = true
    }

     fun onGameCompleted(){
        _eventGameFinish.value = false
    }

}