package com.example.sport_app.data.dao

import androidx.room.*
import com.example.sport_app.data.models.WorkoutEntityModel

@Dao
interface WorkoutDAO {

    @Insert
    suspend fun insertWorkout(workout: WorkoutEntityModel)

    @Query("SELECT * FROM workouts WHERE userId = :userId")
    suspend fun getWorkoutsForUser(userId: Long): List<WorkoutEntityModel>

    @Delete
    suspend fun deleteWorkout(workout: WorkoutEntityModel)

    @Update
    suspend fun updateWorkout(workout: WorkoutEntityModel)
}
