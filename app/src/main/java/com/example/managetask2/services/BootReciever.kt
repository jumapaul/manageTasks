package com.example.managetask2.services

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.managetask2.domain.repository.TaskManagerRepository
import com.example.managetask2.domain.use_cases.DailyReminderUseCase
import javax.inject.Inject

class BootReceiver @Inject constructor(
    val repository: TaskManagerRepository
) : BroadcastReceiver() {
    private val dailyReminderUseCase = DailyReminderUseCase(repository)
    override fun onReceive(context: Context, intent: Intent?) {
        if (intent?.action == "android.intent.BOOT_COMPLETED") {
            repository.getAllTasks().forEach {
                dailyReminderUseCase.executeDailyReminder(
                    context,
                    reminderTime = it.time,
                    it,
                    reminderId = 0
                )
            }
        }
    }
}