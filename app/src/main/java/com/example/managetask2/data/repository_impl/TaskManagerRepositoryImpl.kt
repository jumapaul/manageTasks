package com.example.managetask2.data.repository_impl

import com.example.managetask2.data.database.TaskerMangerDao
import com.example.managetask2.data.entity.TaskData
import com.example.managetask2.data.entity.User
import com.example.managetask2.domain.repository.TaskManagerRepository

class TaskManagerRepositoryImpl(
    private val dao: TaskerMangerDao
) : TaskManagerRepository {
    override fun getAllTasks(): List<TaskData> = dao.getAllTasks()

    override suspend fun addTask(data: TaskData) = dao.addTasks(data)

    //override suspend fun getTaskById(id: Int): TaskData = dao.getTaskById(id)

    //override fun getByDate(date: String): List<TaskData> = dao.getCategory(date)

    //override fun getImportant(important: Boolean): List<TaskData> = dao.getImportant(important)
}

//    override fun getAllBusinessTasks(): List<TaskData>{
//        return dao.getAllTasks().filter { it.category == CategoryType.BUSINESS.name }
//    }
//
//    override fun getAllHealthTasks(): List<TaskData> {
//        return dao.getAllTasks().filter { it.category == CategoryType.HEALTH.name }
//    }
//
//    override fun getAllEntertainmentTasks(): List<TaskData> {
//        return dao.getAllTasks().filter { it.category == CategoryType.ENTERTAINMENT.name }
//    }
//
//    override fun getAllHomeTasks(): List<TaskData> {
//        return dao.getAllTasks().filter { it.category == CategoryType.HOME.name }
//    }