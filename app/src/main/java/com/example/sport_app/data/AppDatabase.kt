package com.example.sport_app.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.sport_app.data.dao.UserDAO
import com.example.sport_app.data.models.UserEntityModel

@Database(
    entities = [UserEntityModel::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract val userDAO: UserDAO
}