package com.example.managetask2.domain.use_cases

import android.content.Context
import com.example.managetask2.data.entity.TaskData
import com.example.managetask2.domain.repository.TaskManagerRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class DailyReminderUseCase @Inject constructor(
    private val repository: TaskManagerRepository,
) {
    fun executeDailyReminder(context: Context, reminderTime: String, taskData: TaskData, reminderId: Int){
        repository.setDailyReminder(context, reminderTime, taskData, reminderId)
    }

    fun executeCancelReminder(context: Context, reminderId: Int){
        repository.cancelDailyReminder(context, reminderId)
    }
}