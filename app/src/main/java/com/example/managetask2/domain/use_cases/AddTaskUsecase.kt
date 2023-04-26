package com.example.managetask2.domain.use_cases

import com.example.managetask2.data.entity.TaskData
import com.example.managetask2.domain.repository.TaskManagerRepository
import javax.inject.Inject

class TaskUseCase @Inject constructor(
    private val repository: TaskManagerRepository
) {
    fun getAllTasks(): List<TaskData> = repository.getAllTasks()
    suspend fun addTask(taskData: TaskData) = repository.addTask(taskData)
    fun getByDate(date: String): List<TaskData> = repository.getByDate(date)

    fun getImportant(important: Boolean): List<TaskData> = repository.getImportant(important)



}