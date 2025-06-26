package com.example.sport_app.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.sport_app.data.dao.UserDAO
import com.example.sport_app.data.dao.WorkoutDAO
import com.example.sport_app.data.models.UserEntityModel
import com.example.sport_app.data.models.WorkoutEntityModel

@Database(
    entities = [UserEntityModel::class, WorkoutEntityModel::class],
    version = 5
)
abstract class AppDatabase : RoomDatabase() {
    abstract val userDAO: UserDAO
    abstract val workoutDAO: WorkoutDAO
}


