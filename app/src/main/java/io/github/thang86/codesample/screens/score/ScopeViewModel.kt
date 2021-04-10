package io.github.thang86.codesample.screens.score

import android.util.Log
import androidx.lifecycle.ViewModel

/**
 *
 * Created by Thang86
 */
class ScopeViewModel(finalScore:Int):ViewModel() {

    var scope = finalScore
    init {
        Log.d("ScopeViewModel","Final score is $finalScore")
    }
}