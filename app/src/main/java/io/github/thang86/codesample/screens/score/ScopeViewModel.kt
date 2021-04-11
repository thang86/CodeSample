package io.github.thang86.codesample.screens.score

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 *
 * Created by Thang86
 */
class ScopeViewModel(finalScore: Int) : ViewModel() {

    private val _score = MutableLiveData(finalScore)
    val score: LiveData<Int>
        get() = _score

    private val _eventPlayAgain = MutableLiveData<Boolean>(false)
    val eventPlayAgain: LiveData<Boolean>
        get() = _eventPlayAgain

    //    var scope = finalScore
    init {
        Log.d("ScopeViewModel", "Final score is $finalScore")
    }

     fun eventPlayAgain(){
        _eventPlayAgain.value = true
    }

    fun eventPlayAgainComplete(){
        _eventPlayAgain.value = false
    }
}