package com.example.managetask2.presentation.screens.start

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.managetask2.R
import com.example.managetask2.databinding.FragmentStartBinding
import com.example.managetask2.presentation.screens.start.StartViewModel.Companion.ACCOUNT_OPTION_FRAGMENT
import com.example.managetask2.presentation.screens.start.StartViewModel.Companion.HOME_FRAGMENT
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class StartFragment : Fragment() {

    lateinit var binding: FragmentStartBinding
    private val viewModel by viewModels<StartViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentStartBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launchWhenStarted {
            viewModel.navigate.collect {
                when (it) {
                    HOME_FRAGMENT -> {
                        findNavController().navigate(R.id.action_startFragment_to_homeScreen)
                    }

                    ACCOUNT_OPTION_FRAGMENT -> {
                        findNavController().navigate(it)
                    }
                }
            }
        }

        binding.btnStart.setOnClickListener {
            viewModel.startButtonIsClicked()
            findNavController().navigate(R.id.action_startFragment_to_accountOptionFragment)
        }
    }

}