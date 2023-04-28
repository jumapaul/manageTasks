package com.example.managetask2.presentation.screens.register

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.managetask2.R
import com.example.managetask2.data.entity.RegisterValidation
import com.example.managetask2.data.entity.User
import com.example.managetask2.databinding.FragmentRegisterBinding
import com.example.managetask2.util.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class RegisterFragment : Fragment() {

    lateinit var binding: FragmentRegisterBinding
    private val viewModel by viewModels<RegisterViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegisterBinding.inflate(inflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            btnRegister.setOnClickListener {
//                val firstName = etUserRegistrationFirstName.text.toString().trim()
//                val lastName = etUserRegistrationLastName.text.toString().trim()
//                val email = etRegisterEmailAddress.text.toString().trim()
//                val password = etRegisterPassword.text.toString()
//                val userDetail = User(firstName, lastName, email)
                val user = User(
                    etUserRegistrationFirstName.text.toString().trim(),
                    etUserRegistrationLastName.text.toString().trim(),
                    etRegisterEmailAddress.text.toString().trim()
                )
                val password = etRegisterPassword.text.toString()

                viewModel.createAccountWithEmailPassword(user, password)

            }

            tvLogin.setOnClickListener {
                findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
            }
        }

        lifecycleScope.launchWhenStarted {
            viewModel.register.collect{
                when(it){
                    is Resource.Loading -> {
                        binding.btnRegister.startAnimation()
                    }

                    is Resource.Success -> {
                        //Log.d("------------------->", "onCreateView: ${it.data.toString()}")
                        findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
                        binding.btnRegister.revertAnimation()
                    }

                    is Resource.Error ->{
                        Log.d("===================>", "onCreateView: ${it.message.toString()}")
                    }

                    else -> Unit
                }
            }
        }

        lifecycleScope.launchWhenStarted {
            viewModel.validation.collect{ validation->
                if (validation.email is RegisterValidation.Failed){
                    withContext(Dispatchers.Main){
                        binding.etRegisterEmailAddress.apply {
                            requestFocus()
                            error = validation.email.toString()
                        }
                    }
                }

                if (validation.password is RegisterValidation.Failed){
                    withContext(Dispatchers.Main){
                        binding.etRegisterPassword.apply {
                            requestFocus()
                            error = validation.password.message
                        }
                    }
                }
            }
        }
    }
}