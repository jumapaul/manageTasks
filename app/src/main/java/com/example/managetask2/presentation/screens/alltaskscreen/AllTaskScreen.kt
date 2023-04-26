package com.example.managetask2.presentation.screens.alltaskscreen

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.managetask2.R
import com.example.managetask2.data.entity.TaskData
import com.example.managetask2.databinding.FragmentAddTaskScreenBinding
import com.example.managetask2.databinding.FragmentAllTaskScreenBinding
import com.example.managetask2.databinding.TaskRecycleviewBinding
import com.example.managetask2.presentation.adapters.AllTasksRecycleViewAdapters
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AllTaskScreen : Fragment() {
    lateinit var binding: FragmentAllTaskScreenBinding
    private val viewModel: AllTaskViewModel by viewModels()
    lateinit var addTaskScreenBinding: FragmentAddTaskScreenBinding
    lateinit var taskRecycleviewBinding: TaskRecycleviewBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAllTaskScreenBinding.inflate(inflater, container, false)
        addTaskScreenBinding =
            FragmentAddTaskScreenBinding.inflate(LayoutInflater.from(requireContext()))
        taskRecycleviewBinding = TaskRecycleviewBinding.inflate(LayoutInflater.from(requireContext()))
        observeAllTaskRecycleView()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.ivExitAllTask.setOnClickListener {
            findNavController().navigate(R.id.action_allTaskScreen_to_addTaskScreen)
        }
    }

    private fun observeAllTaskRecycleView() {

        viewModel.list.observe(viewLifecycleOwner) {
            val groupedData= it.groupBy { item-> item.category }
            groupedData.forEach { (cat, tasks) ->
                when(cat){
                    "BUSINESS" ->{
                        setUpCategoryRecycleView(cat,tasks)
                        binding.llBusiness.visibility = View.VISIBLE
                    }
                    "HEALTH" ->{
                        setUpCategoryRecycleView(cat,tasks)
                        binding.llHealth.visibility = View.VISIBLE
                    }
                    "ENTERTAINMENT" ->{
                        setUpCategoryRecycleView(cat,tasks)
                        binding.llEntertainment.visibility = View.VISIBLE
                    }
                    "HOME" ->{
                        setUpCategoryRecycleView(cat,tasks)
                        binding.llHome.visibility = View.VISIBLE
                    }
                }
            }

        }
    }

    private fun setUpCategoryRecycleView(category: String, tasks: List<TaskData>) {
        when (category) {
            "HOME" -> {
                val allTasksRecycleViewAdapters = AllTasksRecycleViewAdapters()
                allTasksRecycleViewAdapters.addTask(tasks)
                binding.rvHome.apply {
                    isNestedScrollingEnabled = false
                    hasFixedSize()
                    adapter = allTasksRecycleViewAdapters
                    layoutManager =
                        LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
                }
            }
            "BUSINESS" -> {
                val allTasksRecycleViewAdapters = AllTasksRecycleViewAdapters()
                allTasksRecycleViewAdapters.addTask(tasks)
                binding.rvBusiness.apply {
                    isNestedScrollingEnabled = false
                    hasFixedSize()
                    adapter = allTasksRecycleViewAdapters
                    layoutManager =
                        LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
                }
            }

            "HEALTH" -> {
                val allTasksRecycleViewAdapters = AllTasksRecycleViewAdapters()
                allTasksRecycleViewAdapters.addTask(tasks)
                binding.rvHealth.apply {
                    isNestedScrollingEnabled = false
                    hasFixedSize()
                    adapter = allTasksRecycleViewAdapters
                    layoutManager =
                        LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
                }
            }

            "ENTERTAINMENT" -> {
                val allTasksRecycleViewAdapters = AllTasksRecycleViewAdapters()
                allTasksRecycleViewAdapters.addTask(tasks)
                binding.rvEntertainment.apply {
                    isNestedScrollingEnabled = false
                    hasFixedSize()
                    adapter = allTasksRecycleViewAdapters
                    layoutManager =
                        LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
                }
            }
        }
    }
}