package com.example.managetask2.domain.repository

import androidx.lifecycle.LiveData
import com.example.managetask2.data.entity.TaskData

interface TaskManagerRepository {
    fun getAllTasks(): LiveData<List<TaskData>>
    suspend fun getTaskById(id: Int): TaskData
    suspend fun addTask(
        data: TaskData,
//        repeatType: RepeatType,
//        tagsType: TagsType,
//        listType: ListType
    )
}