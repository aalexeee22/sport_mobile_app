package com.example.sport_app.data.dao

import androidx.room.Dao
import androidx.room.Insert
import com.example.sport_app.data.models.UserEntityModel

@Dao
interface UserDAO {
    @Insert
    suspend fun insert(user: UserEntityModel)
}