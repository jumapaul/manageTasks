package com.example.managetask2.presentation.screens.alltaskscreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.managetask2.R
import com.example.managetask2.databinding.FragmentAllTaskScreenBinding

class AllTaskScreen : Fragment() {

    lateinit var binding: FragmentAllTaskScreenBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAllTaskScreenBinding.inflate(inflater, container, false)
        return inflater.inflate(R.layout.fragment_all_task_screen, container, false)
    }
}