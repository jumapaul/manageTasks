package com.example.managetask2.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.managetask2.presentation.component_data.ListData
import com.example.managetask2.presentation.component_data.RadioButtonsData
import com.example.managetask2.presentation.component_data.TagsData

@Entity("tasks")
data class TaskData(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val description: String,
    val isImportant: Boolean,

    //val date: String,
    //val time: String,
    //val repeat: List<RadioButtonsData>?,
    // val tags: List<TagsData>?,
    //val imageUrl: String?,
    // val list: List<ListData>?
)