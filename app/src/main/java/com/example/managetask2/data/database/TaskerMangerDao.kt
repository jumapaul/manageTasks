package com.example.managetask2.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.managetask2.data.entity.TaskData

@Dao
interface TaskerMangerDao {

    @Query("SELECT * FROM tasks")
    fun getAllTasks(): List<TaskData>

    @Query("SELECT * FROM tasks where id =:id")
    suspend fun getTaskById(id: Int): TaskData

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addTasks(taskData: TaskData)
}