package com.example.managetask2.presentation.screens.addtaskscreen

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import com.example.managetask2.R
import com.example.managetask2.data.entity.TaskData
import com.example.managetask2.presentation.component_data.RadioButtonsData
import com.example.managetask2.presentation.component_data.TagsData
import com.example.managetask2.databinding.ChipsBinding
import com.example.managetask2.databinding.FragmentAddTaskScreenBinding
import com.example.managetask2.databinding.RadioButtonItemBinding
import com.example.managetask2.presentation.adapters.ExpandableRadioButtonsAdapter
import com.example.managetask2.presentation.adapters.ExpandableTagsAdapter
import dagger.hilt.android.AndroidEntryPoint
import java.util.Calendar

@AndroidEntryPoint
class AddTaskScreen : Fragment() {

    lateinit var binding: FragmentAddTaskScreenBinding
    lateinit var itemBinding: RadioButtonItemBinding
    lateinit var chipItemBinding: ChipsBinding
    private val taskManagerViewModel: AddTaskViewModel by viewModels()

    @RequiresApi(Build.VERSION_CODES.N)
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
        binding.apply {
            ivCalender.setOnClickListener {
                addDate()
            }
            ivTime.setOnClickListener {
                addTime()
            }
            val title = etTitle.text.toString()
            val description = etDescription.text.toString()
            val important = rbImportant.isChecked

            btnSave.setOnClickListener {

                val task = TaskData(0, title, description, important)
                taskManagerViewModel.addTask(task)

                Toast.makeText(requireContext(), "Saved successfully", Toast.LENGTH_SHORT)
                    .show()
            }
        }
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

    fun setUpTagsDropDown() {
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

    @SuppressLint("SetTextI18n")
    @RequiresApi(Build.VERSION_CODES.N)
    fun addDate() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            requireContext(), { view, year, month, day ->
                binding.tvDate.text = (day.toString() + "-" + (month + 1) + "-" + year)
            },
            year, month, day
        )
        datePickerDialog.datePicker.minDate = calendar.timeInMillis
        datePickerDialog.show()
    }

    @SuppressLint("SetTextI18n")
    fun addTime() {
        val calender = Calendar.getInstance()
        var hour = calender.get(Calendar.HOUR_OF_DAY)
        val minute = calender.get(Calendar.MINUTE)

        val timePickerDialog = TimePickerDialog(
            requireContext(), { view, hourOfDay, minute ->
                var am_pm = ""
                var formattedHour = ""
                var formattedMinute = ""
                when {
                    hourOfDay == 0 -> {
                        formattedHour = "${hourOfDay + 12}"
                        am_pm = "AM"
                        if (minute < 10) {
                            formattedMinute = "0$minute"
                        } else formattedMinute = minute.toString()
                    }
                    hourOfDay > 12 -> {
                        formattedHour = "${hourOfDay - 12}"
                        am_pm = "PM"
                        if (minute < 10) {
                            formattedMinute = "0$minute"
                        } else formattedMinute = minute.toString()
                    }
                    hourOfDay == 12 -> {
                        formattedHour = "$hourOfDay"
                        am_pm = "PM"
                        if (minute < 10) {
                            formattedMinute = "0$minute"
                        } else formattedMinute = minute.toString()
                    }
                    else -> {
                        formattedHour = "$hourOfDay"
                        am_pm = "AM"
                        if (minute < 10) {
                            formattedMinute = "0$minute"
                        } else formattedMinute = minute.toString()
                    }
                }

                Log.d("============>", "addTime: $hour")
                Log.d("----------->", "addTime: $hourOfDay")
                binding.tvTime.text = "$formattedHour: $formattedMinute $am_pm"
            },
            hour, minute, false
        )
        timePickerDialog.show()
    }
}