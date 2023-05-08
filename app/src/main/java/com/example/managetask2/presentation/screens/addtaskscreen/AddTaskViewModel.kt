package com.example.managetask2.presentation.screens.addtaskscreen

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.managetask2.constants.Constants
import com.example.managetask2.constants.Constants.REMINDER_NOTIFICATION_REQUEST_CODE
import com.example.managetask2.data.entity.TaskData
import com.example.managetask2.domain.repository.TaskManagerRepository
import com.example.managetask2.domain.use_cases.DailyReminderUseCase
import com.example.managetask2.domain.use_cases.TaskUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddTaskViewModel @Inject constructor(
    private val taskUseCase:TaskUseCase,
    private val dailyReminderUseCase:DailyReminderUseCase
) : ViewModel() {
    fun addTask(taskData: TaskData) = viewModelScope.launch(Dispatchers.IO) {
        taskUseCase.addTask(taskData)
    }

    fun setReminder(context: Context, taskData: TaskData, reminderTime: String, reminderId: Int) = viewModelScope.launch(Dispatchers.IO) {
        dailyReminderUseCase.executeDailyReminder(
            context, reminderTime, taskData, reminderId
        )
    }

    fun cancelReminder(context: Context, reminderId: Int){
        dailyReminderUseCase.executeCancelReminder(context, reminderId)
    }


}