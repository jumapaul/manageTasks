package com.example.managetask2.domain.repository

import androidx.lifecycle.LiveData
import com.example.managetask2.data.entity.TaskData

interface TaskManagerRepository {
    fun getAllTasks(): List<TaskData>
    suspend fun getTaskById(id: Int): TaskData
    suspend fun addTask(
        data: TaskData,
    )

    fun getAllBusinessTasks(): List<TaskData>

    fun getAllHealthTasks(): List<TaskData>

    fun getAllEntertainmentTasks(): List<TaskData>

    fun getAllHomeTasks(): List<TaskData>
}