package com.example.managetask2.data.repository_impl

import com.example.managetask2.data.database.TaskerMangerDao
import com.example.managetask2.data.entity.CategoryType
import com.example.managetask2.data.entity.TaskData
import com.example.managetask2.domain.repository.TaskManagerRepository

class TaskManagerRepositoryImpl(
    private val dao: TaskerMangerDao
) : TaskManagerRepository {
    override fun getAllTasks(): List<TaskData> {
        var result = dao.getAllTasks()
        return result
    }

    override suspend fun getTaskById(id: Int): TaskData {
        return dao.getTaskById(id)
    }

    override suspend fun addTask(
        data: TaskData
    ) {
        dao.addTasks(data)
    }

    override fun getAllBusinessTasks(): List<TaskData>{
        return dao.getAllTasks().filter { it.category == CategoryType.BUSINESS.name }
    }

    override fun getAllHealthTasks(): List<TaskData> {
        return dao.getAllTasks().filter { it.category == CategoryType.HEALTH.name }
    }

    override fun getAllEntertainmentTasks(): List<TaskData> {
        return dao.getAllTasks().filter { it.category == CategoryType.ENTERTAINMENT.name }
    }

    override fun getAllHomeTasks(): List<TaskData> {
        return dao.getAllTasks().filter { it.category == CategoryType.HOME.name }
    }
}