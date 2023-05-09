package com.example.managetask2.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.managetask2.data.entity.TaskData
import com.example.managetask2.data.entity.User

@Dao
interface TaskerMangerDao {

    @Query("SELECT * FROM tasks")
    fun getAllTasks(): List<TaskData>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addTasks(taskData: TaskData)

    @Query("SELECT * FROM tasks ORDER BY date ASC, time ASC")
    fun getAllTasksSortedByDateTime(): List<TaskData>

    @Query("SELECT * FROM tasks WHERE isImportant = 1")
    fun getAllImportantTasks(): List<TaskData>

    @Query("SELECT * FROM tasks WHERE date =:today")
    fun getAllTodayTask(today: String): List<TaskData>

    @Delete
    suspend fun deleteTask(taskData: TaskData)
}