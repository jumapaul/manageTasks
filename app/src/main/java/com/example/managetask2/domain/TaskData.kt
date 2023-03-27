package com.example.managetask2.domain

import com.example.managetask2.data.ListData
import com.example.managetask2.data.RadioButtonsData
import com.example.managetask2.data.TagsData

data class TaskData(
    val id: Int,
    val title: String,
    val description: String,
    val date: String,
    val time: String,
    val repeat: List<RadioButtonsData>,
    val tags: List<TagsData>,
    val isImportant: Boolean,
    val image: Int,
    val list: List<ListData>
)