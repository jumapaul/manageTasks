package com.example.managetask2.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

data class User(
    val firstName: String,
    val lastName: String,
    val emailAddress: String,
    //val imageUrl: String
){
    constructor(): this("", "", "")
}
