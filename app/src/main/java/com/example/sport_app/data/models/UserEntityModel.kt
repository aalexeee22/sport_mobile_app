package com.example.sport_app.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserEntityModel (
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val fullname: String,
    val email: String,
    val password: String
)