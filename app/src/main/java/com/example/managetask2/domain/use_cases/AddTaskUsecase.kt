package com.example.managetask2.domain.use_cases

import androidx.lifecycle.LiveData
import com.example.managetask2.data.entity.TaskData
import com.example.managetask2.domain.repository.TaskManagerRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AddTaskUseCase @Inject constructor(
    private val repository: TaskManagerRepository
) {
    fun getAllTasks(): List<TaskData> = repository.getAllTasks()
    suspend fun addTask(taskData: TaskData) {
        repository.addTask(taskData)
    }
}