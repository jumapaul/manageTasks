package com.example.managetask2.domain.repository

import android.content.Context
import com.example.managetask2.data.entity.TaskData
import com.example.managetask2.data.entity.User
import com.google.android.gms.tasks.Task

interface TaskManagerRepository {
    fun getAllTasks(): List<TaskData>
    suspend fun addTask(data: TaskData)

    fun setDailyReminder(context: Context, reminderTime: String,taskData: TaskData, reminderId: Int)

    fun cancelDailyReminder(context: Context, reminderId: Int)

    fun getAllTasksSortedByDateTime(): List<TaskData>

    fun getAllImportantTasks(): List<TaskData>

    fun getTodayTasks(today: String): List<TaskData>

    suspend fun deleteTask(taskData: TaskData)
}