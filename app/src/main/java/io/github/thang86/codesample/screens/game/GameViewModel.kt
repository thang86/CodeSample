package io.github.thang86.codesample.screens.game

import android.os.CountDownTimer
import android.text.format.DateUtils
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

/**
 *
 * Created by Thang86
 */
class GameViewModel : ViewModel() {

    companion object{
        // Time when the game is over
        private const val DONE = 0L

        // Countdown time interval
        private const val ONE_SECOND = 1000L

        // Total time for the game
        private const val COUNTDOWN_TIME = 60000L
    }

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

    private val _currentTime = MutableLiveData<Long>()

    val currentTime:LiveData<Long>
        get() = _currentTime

    private val timer:CountDownTimer


    init {
        timer = object :CountDownTimer(COUNTDOWN_TIME, ONE_SECOND){
            override fun onTick(millisUntilFinished: Long) {
                _currentTime.value = millisUntilFinished/ ONE_SECOND
            }

            override fun onFinish() {
                _currentTime.value = DONE
                onGameFinish()
            }

        }
        timer.start()
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
            resetList()

        } else {
            //Select and remove a word from the list
            _word.value = wordList.removeAt(0)
        }
    }

    /** Method for the game completed event **/
    override fun onCleared() {
        super.onCleared()
        timer.cancel()
        Log.d("GameViewModel", "GameViewModel onCleared!")
    }

     fun onGameFinish() {
        _eventGameFinish.value = true
    }

     fun onGameCompleted(){
        _eventGameFinish.value = false
    }

    val currentTimeString= Transformations.map(currentTime) { it ->

            DateUtils.formatElapsedTime(it)
    }
    val hintText = Transformations.map(word){ it ->
        val index  = (1..it.length).random()
        "Current word has ${it.length} letters \n The letters at positon ${index} is ${it[index-1]}"

    }

}

