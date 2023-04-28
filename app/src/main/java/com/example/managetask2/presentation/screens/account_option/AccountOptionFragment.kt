package com.example.managetask2.presentation.screens.account_option

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.managetask2.R
import com.example.managetask2.databinding.FragmentAccountOptionBinding

class AccountOptionFragment : Fragment() {

    lateinit var binding: FragmentAccountOptionBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentAccountOptionBinding.inflate(inflater)

        binding.apply {
            btnLoginAccount.setOnClickListener {
                findNavController().navigate(R.id.action_accountOptionFragment_to_loginFragment)
            }

            btnRegisterAccount.setOnClickListener {
                findNavController().navigate(R.id.action_accountOptionFragment_to_registerFragment)
            }
        }
        return binding.root
    }
}