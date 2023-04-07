package com.example.managetask2.presentation.screens.alltaskscreen

import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.managetask2.data.entity.CategoryType
import com.example.managetask2.data.entity.TaskData
import com.example.managetask2.databinding.FragmentAddTaskScreenBinding
import com.example.managetask2.databinding.FragmentAllTaskScreenBinding
import com.example.managetask2.databinding.TaskRecycleviewBinding
import com.example.managetask2.presentation.adapters.recycleviewadapters.AllTasksRecycleViewAdapters
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AllTaskScreen : Fragment() {

    lateinit var binding: FragmentAllTaskScreenBinding
    private val allTasksRecycleViewAdapters: AllTasksRecycleViewAdapters by lazy { AllTasksRecycleViewAdapters() }
    private val viewModel: AllTaskViewModel by viewModels()
    lateinit var taskData: TaskData
    lateinit var addTaskScreenBinding: FragmentAddTaskScreenBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAllTaskScreenBinding.inflate(inflater, container, false)
        addTaskScreenBinding =
            FragmentAddTaskScreenBinding.inflate(LayoutInflater.from(requireContext()))


        val title = addTaskScreenBinding.etTitle.text.toString()
        val description = addTaskScreenBinding.etDescription.text.toString()
        val important = addTaskScreenBinding.rbImportant.isChecked
        val time = addTaskScreenBinding.tvTime.text.toString()
        val date = addTaskScreenBinding.tvDate.text.toString()

        taskData =
            TaskData(0, title, description, important, time, date, CategoryType.HOME.name)
        observeAllTaskRecycleView(taskData)
        return binding.root
    }

    fun observeAllTaskRecycleView(taskData: TaskData) {
        viewModel.list.observe(viewLifecycleOwner) {
            allTasksRecycleViewAdapters.addTask(it)
            setUpBusinessCategoryRecycleView(taskData)
        }
    }

    fun setUpBusinessCategoryRecycleView(taskData: TaskData) {
        when (taskData.category) {
            "BUSINESS" -> {
                binding.rvBusiness.apply {
                    hasFixedSize()
                    adapter = allTasksRecycleViewAdapters
                    layoutManager =
                        LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
                }
            }

            "HEALTH" -> {
                binding.rvHealth.apply {
                    hasFixedSize()
                    adapter = allTasksRecycleViewAdapters
                    layoutManager =
                        LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
                }
            }

            "ENTERTAINMENT" -> {
                binding.rvEntertainment.apply {
                    hasFixedSize()
                    adapter = allTasksRecycleViewAdapters
                    layoutManager =
                        LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
                }
            }

            "HOME" -> {
                binding.rvHome.apply {
                    hasFixedSize()
                    adapter = allTasksRecycleViewAdapters
                    layoutManager =
                        LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
                }
            }

        }
    }
}