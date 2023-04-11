package com.example.managetask2.presentation.screens.homescreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.managetask2.R
import com.example.managetask2.databinding.FragmentHomeScreenBinding
import com.example.managetask2.presentation.adapters.expandableadapters.CategoryAdapter
import com.example.managetask2.presentation.component_data.Category

class HomeScreen : Fragment() {
    lateinit var binding: FragmentHomeScreenBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeScreenBinding.inflate(inflater, container, false)

        setUpCategoryRecycleView()

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

        return binding.root

    }
    fun setUpCategoryRecycleView() {
        val tasksAdapter = Category.category?.let { CategoryAdapter(it, requireContext()) }
        binding.rvTasks.apply {
            hasFixedSize()
            adapter = tasksAdapter
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

        }
    }
}