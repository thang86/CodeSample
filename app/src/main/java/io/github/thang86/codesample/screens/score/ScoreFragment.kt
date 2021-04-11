package io.github.thang86.codesample.screens.score

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import io.github.thang86.codesample.R
import io.github.thang86.codesample.databinding.ScoreFragmentBinding

/**
 *
 * Created by Thang86
 */
class ScoreFragment : Fragment() {

    private lateinit var viewModel: ScopeViewModel
    private lateinit var viewModelFactory: ScoreViewModelFactory

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {

        // Inflate view and obtain an instance of the binding class.
        val binding: ScoreFragmentBinding = DataBindingUtil.inflate(
                inflater,
                R.layout.score_fragment,
                container,
                false
        )

        viewModelFactory = ScoreViewModelFactory(ScoreFragmentArgs.fromBundle(requireArguments()).score)
        viewModel = ViewModelProvider(this,viewModelFactory).get(ScopeViewModel::class.java)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        viewModel.eventPlayAgain.observe(viewLifecycleOwner,{ playAgain ->
            if (playAgain){
                findNavController().navigate(ScoreFragmentDirections.actionRestart())
                viewModel.eventPlayAgainComplete()
            }

        })

        return binding.root
    }
}
