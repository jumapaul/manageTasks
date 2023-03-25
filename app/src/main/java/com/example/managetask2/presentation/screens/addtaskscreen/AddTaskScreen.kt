package com.example.managetask2.presentation.screens.addtaskscreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import com.example.managetask2.R
import com.example.managetask2.data.RadioButtonsData
import com.example.managetask2.data.TagsData
import com.example.managetask2.databinding.ChipsBinding
import com.example.managetask2.databinding.FragmentAddTaskScreenBinding
import com.example.managetask2.databinding.RadioButtonItemBinding
import com.example.managetask2.presentation.adapters.ExpandableRadioButtonsAdapter
import com.example.managetask2.presentation.adapters.ExpandableTagsAdapter

class AddTaskScreen : Fragment() {

    lateinit var binding: FragmentAddTaskScreenBinding
    lateinit var itemBinding: RadioButtonItemBinding
    lateinit var chipItemBinding: ChipsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAddTaskScreenBinding.inflate(inflater, container, false)
        itemBinding = RadioButtonItemBinding.inflate(LayoutInflater.from(requireContext()))
        chipItemBinding = ChipsBinding.inflate(LayoutInflater.from(requireContext()))

        setUpRepeatDropDown()
        setUpTagsDropDown()
        return binding.root
    }

    fun setUpRepeatDropDown() {
        val expandableButtons = binding.elRepeat
        val items = listOf(
            RadioButtonsData(
                "Repeat", R.drawable.ic_baseline_autorenew_24,
                listOf("Never", "Hourly", "By days", "Weekdays", "Weekly", "Monthly", "Annually")
            )
        )
        val adapter = ExpandableRadioButtonsAdapter(requireContext(), items)
        expandableButtons.setAdapter(adapter)

    }

    fun setUpTagsDropDown(){
        val expandableTagsList = binding.elNewTaskTags
        val items = listOf(
            TagsData(
                "Tags", R.drawable.ic_baseline_numbers_24, listOf(
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
        val adapter = ExpandableTagsAdapter(requireContext(), items)
        expandableTagsList.setAdapter(adapter)
    }
}