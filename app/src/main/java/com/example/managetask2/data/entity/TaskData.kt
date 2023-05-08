package com.example.managetask2.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity("tasks")
data class TaskData(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val description: String,
    val date: String,
    val time: String,
    val repeat: String,
    val isImportant: Boolean,
    val tags: List<String>?,
    val imageUrl: String?,
    val category: String
) : Serializable