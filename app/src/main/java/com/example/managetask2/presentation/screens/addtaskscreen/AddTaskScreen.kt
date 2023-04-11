package com.example.managetask2.presentation.screens.addtaskscreen

import android.annotation.SuppressLint
import android.app.Activity
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.AppCompatRadioButton
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.managetask2.R
import com.example.managetask2.data.entity.CategoryType
import com.example.managetask2.data.entity.RepeatType
import com.example.managetask2.data.entity.TaskData
import com.example.managetask2.databinding.FragmentAddTaskScreenBinding
import com.example.managetask2.databinding.TaskRecycleviewBinding
import com.google.android.material.chip.Chip
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
    private var hour = calender.get(Calendar.HOUR_OF_DAY)
    private val minute = calender.get(Calendar.MINUTE)
    var selectedDate = ""
    private var selectedTime = ""
    private val listOfTags = mutableListOf<String>()
    private var imagePath: String? = null
    private val pickMedia =
        registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
            if (uri != null) {
                imagePath = uri.toString()
                Log.d("PhotoPicker", "Selected URI: $uri")
            } else {
                Log.d("PhotoPicker", "No media selected")
            }
        }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAddTaskScreenBinding.inflate(inflater, container, false)
        recycleviewBinding = TaskRecycleviewBinding.inflate(LayoutInflater.from(requireContext()))

        binding.apply {

            tvAddImage.setOnClickListener {

                pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))

            }
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
                if (binding.llTagsGroup.visibility == View.GONE) {
                    binding.llTagsGroup.visibility = View.VISIBLE
                } else {
                    binding.llTagsGroup.visibility = View.GONE
                }
            }

            val title = etTitle.text
            val description = etDescription.text
            binding.rgRepeatType.setOnCheckedChangeListener { group, checkedId ->
                group.findViewById<AppCompatRadioButton>(checkedId)?.let { radioButton ->
                    binding.tvRepeatTypeSelected.text = radioButton.text.toString()
                }
            }

            btnSave.setOnClickListener {
                Log.d("#####################", "onCreateView: $imagePath")

                when (binding.rgRepeatType.checkedRadioButtonId) {
                    binding.rbNever.id -> {
                        imagePath?.let { it1 ->
                            saveTask(
                                title.toString(),
                                description.toString(),
                                binding.tvDate.text.toString(),
                                binding.tvTime.text.toString(),
                                RepeatType.NEVER.name,
                                binding.rbImportant.isChecked,
                                listOfTags,
                                it1,
                                when (binding.rgCategory.checkedRadioButtonId) {
                                    binding.rbBusiness.id -> {
                                        CategoryType.BUSINESS.name
                                    }

                                    binding.rbHealth.id -> {
                                        CategoryType.HEALTH.name
                                    }

                                    binding.rbEntertainment.id -> {
                                        CategoryType.ENTERTAINMENT.name
                                    }

                                    binding.rbHome.id -> {
                                        CategoryType.HOME.name
                                    }

                                    else -> "Invalid"
                                }
                            )
                        }
                    }

                    binding.rbDaily.id -> {
                        imagePath?.let { it1 ->
                            saveTask(
                                title.toString(),
                                description.toString(),
                                binding.tvDate.text.toString(),
                                binding.tvTime.text.toString(),
                                RepeatType.DAILY.name,
                                binding.rbImportant.isChecked,
                                listOfTags,
                                it1,
                                when (binding.rgCategory.checkedRadioButtonId) {
                                    binding.rbBusiness.id -> {
                                        CategoryType.BUSINESS.name
                                    }

                                    binding.rbHealth.id -> {
                                        CategoryType.HEALTH.name
                                    }

                                    binding.rbEntertainment.id -> {
                                        CategoryType.ENTERTAINMENT.name
                                    }

                                    binding.rbHome.id -> {
                                        CategoryType.HOME.name
                                    }

                                    else -> "Invalid"
                                }
                            )
                        }
                    }

                    binding.rbWeekdays.id -> {
                        imagePath?.let { it1 ->
                            saveTask(
                                title.toString(),
                                description.toString(),
                                binding.tvDate.text.toString(),
                                binding.tvTime.text.toString(),
                                RepeatType.WEEKDAYS.name,
                                binding.rbImportant.isChecked,
                                listOfTags,
                                it1,
                                when (binding.rgCategory.checkedRadioButtonId) {
                                    binding.rbBusiness.id -> {
                                        CategoryType.BUSINESS.name
                                    }

                                    binding.rbHealth.id -> {
                                        CategoryType.HEALTH.name
                                    }

                                    binding.rbEntertainment.id -> {
                                        CategoryType.ENTERTAINMENT.name
                                    }

                                    binding.rbHome.id -> {
                                        CategoryType.HOME.name
                                    }

                                    else -> "Invalid"
                                }
                            )
                        }
                    }


                    binding.rbWeekly.id -> {
                        imagePath?.let { it1 ->
                            saveTask(
                                title.toString(),
                                description.toString(),
                                binding.tvDate.text.toString(),
                                binding.tvTime.text.toString(),
                                RepeatType.WEEKLY.name,
                                binding.rbImportant.isChecked,
                                listOfTags,
                                it1,
                                when (binding.rgCategory.checkedRadioButtonId) {
                                    binding.rbBusiness.id -> {
                                        CategoryType.BUSINESS.name
                                    }

                                    binding.rbHealth.id -> {
                                        CategoryType.HEALTH.name
                                    }

                                    binding.rbEntertainment.id -> {
                                        CategoryType.ENTERTAINMENT.name
                                    }

                                    binding.rbHome.id -> {
                                        CategoryType.HOME.name
                                    }

                                    else -> "Invalid"
                                }
                            )
                        }
                    }

                    binding.rbMonthly.id -> {
                        imagePath?.let { it1 ->
                            saveTask(
                                title.toString(),
                                description.toString(),
                                binding.tvDate.text.toString(),
                                binding.tvTime.text.toString(),
                                RepeatType.MONTHLY.name,
                                binding.rbImportant.isChecked,
                                listOfTags,
                                it1,
                                when (binding.rgCategory.checkedRadioButtonId) {
                                    binding.rbBusiness.id -> {
                                        CategoryType.BUSINESS.name
                                    }

                                    binding.rbHealth.id -> {
                                        CategoryType.HEALTH.name
                                    }

                                    binding.rbEntertainment.id -> {
                                        CategoryType.ENTERTAINMENT.name
                                    }

                                    binding.rbHome.id -> {
                                        CategoryType.HOME.name
                                    }

                                    else -> "Invalid"
                                }
                            )
                        }
                    }

                    binding.rbAnnually.id -> {
                        imagePath?.let { it1 ->
                            saveTask(
                                title.toString(),
                                description.toString(),
                                binding.tvDate.text.toString(),
                                binding.tvTime.text.toString(),
                                RepeatType.ANNUALLY.name,
                                binding.rbImportant.isChecked,
                                listOfTags,
                                it1,
                                when (binding.rgCategory.checkedRadioButtonId) {
                                    binding.rbBusiness.id -> {
                                        CategoryType.BUSINESS.name
                                    }

                                    binding.rbHealth.id -> {
                                        CategoryType.HEALTH.name
                                    }

                                    binding.rbEntertainment.id -> {
                                        CategoryType.ENTERTAINMENT.name
                                    }

                                    binding.rbHome.id -> {
                                        CategoryType.HOME.name
                                    }

                                    else -> "Invalid"
                                }
                            )
                        }
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tagGroup = binding.cgAllTags
        binding.cgAllTags.setOnCheckedStateChangeListener { group, checkedIds ->
            for (i in 0 until tagGroup.childCount) {
                val tag = tagGroup.getChildAt(i) as Chip

                tag.setOnCheckedChangeListener { buttonView, isChecked ->
                    val text = tag.text.toString()

                    if (isChecked) listOfTags.add(text)
                    else listOfTags.remove(text)
                }
            }
        }
    }
    private fun saveTask(
        title: String,
        description: String,
        date: CharSequence,
        time: CharSequence,
        repeat: String,
        important: Boolean,
        tags: List<String>,
        imageUrl: String,
        category: String,
    ) {
        val task = TaskData(
            0,
            title,
            description,
            date.toString(),
            time.toString(),
            repeat,
            important,
            tags,
            imageUrl,
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
