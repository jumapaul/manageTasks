package com.example.managetask2.presentation.screens.alltaskscreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.managetask2.data.entity.CategoryType
import com.example.managetask2.data.entity.RepeatType
import com.example.managetask2.data.entity.TaskData
import com.example.managetask2.databinding.FragmentAddTaskScreenBinding
import com.example.managetask2.databinding.FragmentAllTaskScreenBinding
import com.example.managetask2.presentation.adapters.recycleviewadapters.AllTasksRecycleViewAdapters
import com.google.android.material.chip.Chip
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AllTaskScreen : Fragment() {

    lateinit var binding: FragmentAllTaskScreenBinding
    private val allTasksRecycleViewAdapters: AllTasksRecycleViewAdapters by lazy { AllTasksRecycleViewAdapters() }
    private val viewModel: AllTaskViewModel by viewModels()
    lateinit var addTaskScreenBinding: FragmentAddTaskScreenBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAllTaskScreenBinding.inflate(inflater, container, false)
        addTaskScreenBinding =
            FragmentAddTaskScreenBinding.inflate(LayoutInflater.from(requireContext()))
        observeAllTaskRecycleView()
        return binding.root
    }

    fun observeAllTaskRecycleView() {
        viewModel.list.observe(viewLifecycleOwner) {
            allTasksRecycleViewAdapters.addTask(it)
            val groupedData= it.groupBy { item-> item.category }

            groupedData.forEach { (cat, _) ->
                when(cat){
                    "BUSINESS" ->{
                        setUpBusinessCategoryRecycleView(cat)
                        binding.llBusiness.visibility = View.VISIBLE
                    }
                    "HEALTH" ->{
                        setUpBusinessCategoryRecycleView(cat)
                        binding.llHealth.visibility = View.VISIBLE
                    }
                    "ENTERTAINMENT" ->{
                        setUpBusinessCategoryRecycleView(cat)
                        binding.llEntertainment.visibility = View.VISIBLE
                    }
                    "HOME" ->{
                        setUpBusinessCategoryRecycleView(cat)
                        binding.llHome.visibility = View.VISIBLE
                    }
                }
            }

        }
    }

    fun setUpBusinessCategoryRecycleView(category:String) {
        when (category) {
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