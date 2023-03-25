package com.example.managetask2.data

import de.hdodenhof.circleimageview.CircleImageView

data class ListData(
    val title: String,
    val icon: Int,
    val backgroundColor: Int,
    val items: List<String>
)