package com.example.managetask2.presentation.screens.addtaskscreen

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.managetask2.R
import com.example.managetask2.data.entity.CategoryType
import com.example.managetask2.data.entity.TaskData
import com.example.managetask2.databinding.FragmentAddTaskScreenBinding
import com.example.managetask2.databinding.TaskRecycleviewBinding
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@AndroidEntryPoint
class AddTaskScreen : Fragment() {

    lateinit var binding: FragmentAddTaskScreenBinding
    lateinit var recycleviewBinding: TaskRecycleviewBinding
    private val taskManagerViewModel: AddTaskViewModel by viewModels()
    val calender = Calendar.getInstance()
    var hour = calender.get(Calendar.HOUR_OF_DAY)
    val minute = calender.get(Calendar.MINUTE)
    var selectedDate = ""
    var selectedTime = ""

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAddTaskScreenBinding.inflate(inflater, container, false)
        recycleviewBinding = TaskRecycleviewBinding.inflate(LayoutInflater.from(requireContext()))

        binding.apply {
            ivCalender.setOnClickListener {
                addDate()
            }

            ivTime.setOnClickListener {
                addTime()
            }

            llRepeatHeader.setOnClickListener {
                if (binding.llRepeatType.visibility == View.GONE) {
                    binding.llRepeatType.visibility = View.VISIBLE
                } else {
                    binding.llRepeatType.visibility = View.GONE
                }
            }

            llTagsHeader.setOnClickListener {
                if (binding.llTagsGroup.visibility == View.GONE){
                    binding.llTagsGroup.visibility = View.VISIBLE
                }else{
                    binding.llTagsGroup.visibility = View.GONE
                }
            }


            val title = etTitle.text.toString()
            val description = etDescription.text.toString()
            val important = rbImportant.isChecked

            btnSave.setOnClickListener {

                when (binding.rgCategory.checkedRadioButtonId) {
                    binding.rbBusiness.id -> {
                        saveTask(
                            title,
                            description,
                            important,
                            binding.tvTime.text.toString(),
                            binding.tvDate.text.toString(),
                            CategoryType.BUSINESS.name
                        )
                    }

                    binding.rbHealth.id -> {
                        saveTask(
                            title, description, important, binding.tvTime.text.toString(),
                            binding.tvDate.text.toString(), CategoryType.HEALTH.name
                        )
                    }

                    binding.rbEntertainment.id -> {
                        saveTask(
                            title,
                            description,
                            important,
                            binding.tvTime.text.toString(),
                            binding.tvDate.text.toString(),
                            CategoryType.ENTERTAINMENT.name
                        )
                    }

                    binding.rbHome.id -> {
                        saveTask(
                            title, description, important, binding.tvTime.text.toString(),
                            binding.tvDate.text.toString(), CategoryType.HOME.name
                        )
                    }
                }
                Toast.makeText(requireContext(), "Saved successfully", Toast.LENGTH_SHORT)
                    .show()

                findNavController().navigate(R.id.action_addTaskScreen_to_allTaskScreen)
            }

            ivBackArrow.setOnClickListener {
                findNavController().navigate(R.id.action_addTaskScreen_to_allTaskScreen)
            }
        }

        return binding.root
    }

    private fun saveTask(
        title: String,
        description: String,
        important: Boolean,
        time: CharSequence,
        date: CharSequence,
        category: String
    ) {
        val task = TaskData(
            0,
            title.toString(),
            description.toString(),
            important,
            time.toString(),
            date.toString(),
            category

        )
        taskManagerViewModel.addTask(task)
    }
    @SuppressLint("SetTextI18n", "SimpleDateFormat")
    fun addDate() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            requireContext(), { view, year, month, day ->
                calendar.set(year, month, day)
                val formatDate = SimpleDateFormat("dd-MMM-yyyy")
                selectedDate = formatDate.format(calendar.time)
                binding.tvDate.text = selectedDate
            },
            year, month, day
        )
        datePickerDialog.datePicker.minDate = calendar.timeInMillis
        datePickerDialog.show()
    }

    @SuppressLint("SetTextI18n")
    fun addTime() {
        val timePickerDialog = TimePickerDialog(
            requireContext(), { view, hourOfDay, minute ->
                val timeString =
                    "${if (hourOfDay < 10) "0${hourOfDay}" else hourOfDay}:${if (minute < 10) "0${minute}" else minute}"
                val timeFormatter = SimpleDateFormat("HH:mm", Locale.getDefault()).parse(timeString)
                selectedTime =
                    timeFormatter?.let {
                        SimpleDateFormat(
                            "hh:mm a",
                            Locale.getDefault()
                        ).format(it)
                    }
                        .toString()

                binding.tvTime.text = selectedTime
            },
            hour, minute, false
        )
        timePickerDialog.show()
    }
}
