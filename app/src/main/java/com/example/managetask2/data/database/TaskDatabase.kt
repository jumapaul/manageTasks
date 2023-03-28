package com.example.managetask2.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.managetask2.data.entity.TaskData

@Database(entities = [TaskData::class], version = 1, exportSchema = false)
abstract class TaskDatabase: RoomDatabase() {
    abstract val dao: TaskerMangerDao

    companion object{
        const val DATABASE_NAME = "MyTask.db"
    }
}