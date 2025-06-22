package com.example.sport_app.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.sport_app.data.models.UserEntityModel

@Dao
interface UserDAO {
    @Insert
    suspend fun insert(user: UserEntityModel)

    @Query("SELECT * FROM users WHERE email = :email AND password = :password LIMIT 1")
    suspend fun getUser(email: String, password: String) : UserEntityModel?

    @Query("SELECT * FROM users WHERE email = :email LIMIT 1")
    suspend fun getUserByEmail(email: String): UserEntityModel?
}