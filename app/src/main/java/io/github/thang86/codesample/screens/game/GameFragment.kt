package io.github.thang86.codesample.screens.game

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import io.github.thang86.codesample.R
import io.github.thang86.codesample.databinding.GameFragmentBinding

/**
 *
 * Created by Thang86
 */
class GameFragment : Fragment() {


    private lateinit var binding: GameFragmentBinding

    private lateinit var gameViewModel: GameViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate view and obtain an instance of the binding class
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.game_fragment,
            container,
            false
        )

        Log.i("GameFragment", "Called ViewModelProvider.get")
        gameViewModel = ViewModelProvider(this).get(GameViewModel::class.java)
        binding.viewModel = gameViewModel
        binding.lifecycleOwner = this

        gameViewModel.eventGameFinish.observe(viewLifecycleOwner, { hasEndGame ->
            if (hasEndGame) {
                gameFinish()
            }
        })

        return binding.root

    }


    private fun gameFinish() {
        Toast.makeText(activity, "Game has just finished", Toast.LENGTH_LONG).show()
        val action = GameFragmentDirections.actionGameToScore()
        action.score = gameViewModel.score.value ?: 0
        NavHostFragment.findNavController(this).navigate(action)
        gameViewModel.onGameCompleted()
    }



}
