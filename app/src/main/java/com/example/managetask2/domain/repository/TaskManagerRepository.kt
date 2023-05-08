package com.example.managetask2.domain.repository

import android.content.Context
import com.example.managetask2.data.entity.TaskData
import com.example.managetask2.data.entity.User

interface TaskManagerRepository {
    fun getAllTasks(): List<TaskData>
    suspend fun addTask(data: TaskData)

    fun setDailyReminder(context: Context, reminderTime: String,taskData: TaskData, reminderId: Int)

    fun cancelDailyReminder(context: Context, reminderId: Int)

    //suspend fun getTaskById(id: Int): TaskData
    // fun getByDate(date: String): List<TaskData>

    // fun getImportant(important: Boolean): List<TaskData>

//    fun getAllBusinessTasks(): List<TaskData>
//
//    fun getAllHealthTasks(): List<TaskData>
//
//    fun getAllEntertainmentTasks(): List<TaskData>

    //fun getAllHomeTasks(): List<TaskData>
}