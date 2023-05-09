package com.example.managetask2.domain.use_cases

import androidx.lifecycle.LiveData
import com.example.managetask2.data.entity.TaskData
import com.example.managetask2.data.entity.User
import com.example.managetask2.domain.repository.TaskManagerRepository
import javax.inject.Inject

class TaskUseCase @Inject constructor(
    private val repository: TaskManagerRepository
) {
    fun getAllTasks(): List<TaskData> = repository.getAllTasks()
    suspend fun addTask(taskData: TaskData) = repository.addTask(taskData)
    fun getAllTasksSortedByDateTime(): List<TaskData> = repository.getAllTasksSortedByDateTime()

    fun getAllImportantTasks(): List<TaskData> = repository.getAllImportantTasks()

    fun getAllTodayTasks(today: String): List<TaskData> = repository.getTodayTasks(today)

    suspend fun deleteTask(taskData: TaskData) = repository.deleteTask(taskData)
}