package com.example.managetask2.data.repository_impl

import androidx.lifecycle.LiveData
import com.example.managetask2.data.database.TaskerMangerDao
import com.example.managetask2.data.entity.TaskData
import com.example.managetask2.domain.repository.TaskManagerRepository

class TaskManagerRepositoryImpl(
    private val dao: TaskerMangerDao
): TaskManagerRepository {
    override fun getAllTasks(): LiveData<List<TaskData>> {
        return dao.getAllTasks()
    }

    override suspend fun getTaskById(id: Int): TaskData {
        return dao.getTaskById(id)
    }

    override suspend fun addTask(
        data: TaskData,
        //repeatType: RepeatType,
       // tagsType: TagsType,
       // listType: ListType
    ) {
        dao.addTasks(data)
    }
}