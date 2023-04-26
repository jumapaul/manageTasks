package com.example.managetask2.presentation.screens.homescreen

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.managetask2.R
import com.example.managetask2.data.entity.TaskData
import com.example.managetask2.databinding.CategoryRecycleviewBinding
import com.example.managetask2.databinding.FragmentHomeScreenBinding
import com.example.managetask2.presentation.adapters.CategoryAdapter
import com.example.managetask2.presentation.adapters.HomeBodyAdapter
import com.example.managetask2.presentation.component_data.Category
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeScreen : Fragment() {
    private lateinit var binding: FragmentHomeScreenBinding
    private val viewModel by viewModels<HomeViewModel>()
    lateinit var adapterBinding: CategoryRecycleviewBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeScreenBinding.inflate(inflater, container, false)
        adapterBinding = CategoryRecycleviewBinding.inflate(LayoutInflater.from(requireContext()))

        observeCategoryRecycleView()
        binding.apply {
            fbAdd.setOnClickListener {
                findNavController().navigate(R.id.action_homeScreen_to_addTaskScreen)
            }

            llHomeTagsHeader.setOnClickListener {
                if (binding.llHomeTagsGroup.visibility == View.GONE) {
                    binding.llHomeTagsGroup.visibility = View.VISIBLE
                    binding.ivShowTags.setImageResource(R.drawable.baseline_expand_less_24)
                } else {
                    binding.llHomeTagsGroup.visibility = View.GONE
                    binding.ivShowTags.setImageResource(R.drawable.baseline_expand_more_24)
                }
            }

            llBusinessHeader.setOnClickListener {
                if (binding.llBusinessBody.visibility == View.GONE) binding.llBusinessBody.visibility =
                    View.VISIBLE else
                    binding.llBusinessBody.visibility = View.GONE
            }
            llHealthHeader.setOnClickListener {
                if (binding.llHealthBody.visibility == View.GONE) binding.llHealthBody.visibility =
                    View.VISIBLE else
                    binding.llHealthBody.visibility = View.GONE
            }
            llEntertainmentHeader.setOnClickListener {
                if (binding.llEntertainmentBody.visibility == View.GONE) binding.llEntertainmentBody.visibility =
                    View.VISIBLE else
                    binding.llEntertainmentBody.visibility = View.GONE
            }
            llHomeHeader.setOnClickListener {
                if (binding.llHomeBody.visibility == View.GONE) binding.llHomeBody.visibility =
                    View.VISIBLE else
                    binding.llHomeBody.visibility = View.GONE
            }
        }

        observeHomeBody()

        return binding.root

    }

    private fun observeCategoryRecycleView() {
        viewModel.tasksByDate.observe(viewLifecycleOwner) { taskDate ->
            taskDate.size
        }

        viewModel.allTasks.observe(viewLifecycleOwner) {
            setUpCategoryRecycleView(it)
            adapterBinding.tvNumber.text = it.size.toString()
        }
    }

    private fun setUpCategoryRecycleView(data: List<TaskData>) {
        val tasksAdapter = Category.category?.let { CategoryAdapter(it, requireContext()) }
        tasksAdapter?.addData(data)
        binding.rvTasks.apply {
            hasFixedSize()
            adapter = tasksAdapter
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

        }
    }

    private fun observeHomeBody() {
        viewModel.allTasks.observe(viewLifecycleOwner) {
            val groupedData = it.groupBy { item -> item.category }

            groupedData.forEach { (cat, tasks) ->
                when (cat) {
                    "BUSINESS" -> {
                        val dataCount = tasks.size
                        binding.tvBusinessBodyCount.text = dataCount.toString()
                        setUpHomeBody(cat, tasks)

                    }

                    "HEALTH" -> {
                        val dataCount = tasks.size
                        binding.tvHealthBodyCount.text = dataCount.toString()
                        setUpHomeBody(cat, tasks)
                    }

                    "ENTERTAINMENT" -> {
                        val dataCount = tasks.size
                        binding.tvEntertainmentBodyCount.text = dataCount.toString()
                        setUpHomeBody(cat, tasks)
                    }

                    "HOME" -> {
                        val dataCount = tasks.size
                        binding.tvHomeCategoryCount.text = dataCount.toString()
                        setUpHomeBody(cat, tasks)
                    }
                }
            }
        }
    }

    private fun setUpHomeBody(category: String, item: List<TaskData>) {
        when (category) {
            "BUSINESS" -> {
                val myAdapter = HomeBodyAdapter()
                myAdapter.addItem(item)
                binding.rvBusinessHome.apply {
                    isNestedScrollingEnabled = false
                    hasFixedSize()
                    adapter = myAdapter
                    layoutManager =
                        LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
                }
            }

            "HEALTH" -> {
                val myAdapter = HomeBodyAdapter()
                myAdapter.addItem(item)
                binding.rvHealthHome.apply {
                    isNestedScrollingEnabled = false
                    hasFixedSize()
                    adapter = myAdapter
                    layoutManager =
                        LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
                }

            }

            "ENTERTAINMENT" -> {
                val myAdapter = HomeBodyAdapter()
                myAdapter.addItem(item)
                binding.rvEntertainmentHome.apply {
                    isNestedScrollingEnabled = false
                    hasFixedSize()
                    adapter = myAdapter
                    layoutManager =
                        LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
                }
            }

            "HOME" -> {
                val myAdapter = HomeBodyAdapter()
                myAdapter.addItem(item)
                binding.rvHomeHome.apply {
                    isNestedScrollingEnabled = false
                    hasFixedSize()
                    adapter = myAdapter
                    layoutManager =
                        LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
                }
            }
        }
    }
}