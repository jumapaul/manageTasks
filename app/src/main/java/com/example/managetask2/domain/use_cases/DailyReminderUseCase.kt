package com.example.managetask2.domain.use_cases

import com.example.managetask2.domain.repository.TaskManagerRepository
import javax.inject.Inject

class DailyReminderUseCase @Inject constructor(
    private val repository: TaskManagerRepository
) {
//    fun executeDailyReminder(){
//        repository.setDailyReminder()
//    }
//
//    fun executeCancelReminder(){
//        repository.cancelDailyReminder()
//    }
}