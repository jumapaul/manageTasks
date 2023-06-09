package com.example.managetask2.presentation.screens.addtaskscreen

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.TimePickerDialog
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.AppCompatRadioButton
import androidx.core.content.ContextCompat
import androidx.core.view.isEmpty
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import com.example.managetask2.R
import com.example.managetask2.constants.Constants.NOTIFICATION_ID
import com.example.managetask2.data.entity.CategoryType
import com.example.managetask2.data.entity.RepeatType
import com.example.managetask2.data.entity.TaskData
import com.example.managetask2.databinding.FragmentAddTaskScreenBinding
import com.example.managetask2.databinding.TaskRecycleviewBinding
import com.google.android.gms.tasks.Task
import com.google.android.material.chip.Chip
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.Calendar
import java.util.Date
import java.util.Locale
import kotlin.math.log

@AndroidEntryPoint
class AddTaskScreen : Fragment() {

    lateinit var binding: FragmentAddTaskScreenBinding
    private lateinit var recycleViewBinding: TaskRecycleviewBinding
    private val taskManagerViewModel: AddTaskViewModel by viewModels()
    private val calender = Calendar.getInstance()
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
        recycleViewBinding = TaskRecycleviewBinding.inflate(LayoutInflater.from(requireContext()))

        binding.apply {
            ivClose.setOnClickListener {
                findNavController().navigate(R.id.action_addTaskScreen_to_homeScreen)
            }

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
                if (binding.rgRepeatType.isEmpty()) {
                    RepeatType.NEVER.name
                }
                when (binding.rgRepeatType.checkedRadioButtonId) {
                    binding.rbNever.id -> {
                        saveTask(
                            TaskData(0,
                                title.toString(),
                                description.toString(),
                                binding.tvDate.text.toString(),
                                binding.tvTime.text.toString(),
                                RepeatType.NEVER.name,
                                binding.rbImportant.isChecked,
                                listOfTags,
                                imagePath.orEmpty(),
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
                                })
                        )
                    }

                    binding.rbDaily.id -> {
                        createNotificationsChannel()
                        val taskData = TaskData(
                            0,
                            title.toString(),
                            description.toString(),
                            binding.tvDate.text.toString(),
                            binding.tvTime.text.toString(),
                            RepeatType.DAILY.name,
                            binding.rbImportant.isChecked,
                            listOfTags,
                            imagePath.orEmpty(),
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
                        taskManagerViewModel.setReminder(requireContext(),taskData ,binding.tvTime.toString(), NOTIFICATION_ID)
                        saveTask(taskData)
                    }

                    binding.rbWeekdays.id -> {
                        saveTask(
                           TaskData(0,
                               title.toString(),
                               description.toString(),
                               binding.tvDate.text.toString(),
                               binding.tvTime.text.toString(),
                               RepeatType.WEEKDAYS.name,
                               binding.rbImportant.isChecked,
                               listOfTags,
                               imagePath.orEmpty(),
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
                               })
                        )
                    }


                    binding.rbWeekly.id -> {
                        saveTask(
                            TaskData(0,title.toString(),
                                description.toString(),
                                binding.tvDate.text.toString(),
                                binding.tvTime.text.toString(),
                                RepeatType.WEEKLY.name,
                                binding.rbImportant.isChecked,
                                listOfTags,
                                imagePath.orEmpty(),
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
                                })
                        )
                    }

                    binding.rbMonthly.id -> {

                        saveTask(
                            TaskData(0,
                                title.toString(),
                                description.toString(),
                                binding.tvDate.text.toString(),
                                binding.tvTime.text.toString(),
                                RepeatType.MONTHLY.name,
                                binding.rbImportant.isChecked,
                                listOfTags,
                                imagePath.orEmpty(),
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
                                })
                        )

                    }

                    binding.rbAnnually.id -> {
                        saveTask(
                            TaskData(0,title.toString(),
                                description.toString(),
                                binding.tvDate.text.toString(),
                                binding.tvTime.text.toString(),
                                RepeatType.ANNUALLY.name,
                                binding.rbImportant.isChecked,
                                listOfTags,
                                imagePath.orEmpty(),
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
                                })
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
       taskData: TaskData
    ) {
        taskManagerViewModel.addTask(taskData)
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
                val dateToday = formatDate.format(Date())
                if (selectedDate == dateToday) binding.tvDate.text = "Today"
                else binding.tvDate.text = selectedDate

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

    fun createNotificationsChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                getString(R.string.reminder_notification_id),
                getString(R.string.channel_name),
                NotificationManager.IMPORTANCE_HIGH
            )

            ContextCompat.getSystemService(requireContext(), NotificationManager::class.java)
                ?.createNotificationChannel(channel)
        }
    }

}
