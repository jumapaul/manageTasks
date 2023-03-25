package com.example.managetask2.presentation.screens.homescreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.managetask2.R
import com.example.managetask2.data.Category
import com.example.managetask2.data.TagsData
import com.example.managetask2.data.ListData
import com.example.managetask2.databinding.FragmentHomeScreenBinding
import com.example.managetask2.databinding.ListItemBinding
import com.example.managetask2.databinding.MyListGroupBinding
import com.example.managetask2.presentation.adapters.CategoryAdapter
import com.example.managetask2.presentation.adapters.ExpandableTagsAdapter
import com.example.managetask2.presentation.adapters.MyListAdapter
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup

class HomeScreen : Fragment() {
    lateinit var binding: FragmentHomeScreenBinding
    lateinit var groupBinding: MyListGroupBinding
    lateinit var itemBinding: ListItemBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeScreenBinding.inflate(inflater, container, false)

        setUpCategoryRecycleView()
        setUpTagsDropDown()
        setUpListDropDown()

        binding.fbAdd.setOnClickListener {
            findNavController().navigate(R.id.action_homeScreen_to_addTaskScreen)
        }

        return binding.root

    }

    fun setUpTagsDropDown() {
        val expandableListView = binding.elTags
        val chipItem = view?.findViewById<ChipGroup>(R.id.cptag)
        val items = listOf(
            TagsData(
                "Tags", null, listOf(
                    "All Tags",
                    "#Finance",
                    "#Family",
                    "#Contract",
                    "#Document",
                    "#Business",
                    "Games store",
                    "#Store",
                    "#Food"
                )
            )
        )

        items.flatMap { it.chips }.distinct().forEach { chipText ->
            val chip = Chip(requireContext())
            chip.text = chipText

            chipItem?.addView(chip)

        }

        val adapter = ExpandableTagsAdapter(requireContext(), items)
        expandableListView.setAdapter(adapter)
    }

    fun setUpListDropDown() {

        val items = listOf(
            ListData(
                "Business", R.drawable.ic_baseline_business_center_24, R.color.dark_tortoise,
                listOf(
                    "Meeting the client",
                    "Demanding for the payments",
                    "Finishing the karibu app project"
                )
            ),
            ListData(
                "Health", R.drawable.ic_baseline_favorite_24, R.color.orange,
                listOf(
                    "Checking on the blood pressure",
                    "Checking on sugar level",
                    "Checking on blood level"
                )
            ),
            ListData(
                "Entertainment", R.drawable.ic_baseline_audio_file_24, R.color.light_purple,
                listOf("Going to the club", "Watching 'Go movie series'", "Going for skating")
            ),
            ListData(
                "Home", R.drawable.ic_baseline_folder_24, R.color.important,
                listOf("Going to the club", "Watching 'Go movie series'", "Going for skating")
            )

        )
        val expandableListView = binding.elMyList
        val adapter = MyListAdapter(requireContext(), items)

        expandableListView.setAdapter(adapter)
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