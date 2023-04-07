package com.example.managetask2.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("tasks")
data class TaskData(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val description: String,
    val isImportant: Boolean,
    val time: String,
    val date: String,
    //val imageUrl: String?,
    //val repeat: String,
    // val tags: List<TagsData>?,
    val category: String
)
    // {
//    fun toTask(listType1: ListType): TaskData {
//        return TaskData(id, title, description, isImportant, time, date, listType1)
//    }
//}