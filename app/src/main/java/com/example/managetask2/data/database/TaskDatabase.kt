package com.example.managetask2.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.managetask2.data.database.typeConverters.Converters
import com.example.managetask2.data.entity.TaskData

@Database(entities = [TaskData::class], version = 9, exportSchema = false)
@TypeConverters(Converters::class)
abstract class TaskDatabase: RoomDatabase() {
    abstract val dao: TaskerMangerDao

    companion object{
        const val DATABASE_NAME = "MyTask.db"
    }
}