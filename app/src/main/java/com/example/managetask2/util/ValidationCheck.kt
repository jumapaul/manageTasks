package com.example.managetask2.util

import android.util.Patterns
import com.example.managetask2.data.entity.RegisterValidation

fun validateEmail(email:String): Any{
    if (email.isEmpty()) return RegisterValidation.Failed("Email cannot be empty")

    if (!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        return RegisterValidation.Failed("Wrong email format")

    return RegisterValidation.Success
}

fun validatePassword(password: String): Any{
    if (password.isEmpty()) return RegisterValidation.Failed("Password cannot be empty")

    if (password.length<6) return RegisterValidation.Failed("Password should contain 6 characters")

    return RegisterValidation.Success
}