package io.github.thang86.codesample.screens.score

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

/**
 *
 * Created by Thang86
 */
class ScoreViewModelFactory(private var finalScore:Int):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ScopeViewModel::class.java)){
            return ScopeViewModel(finalScore) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class ")
    }
}