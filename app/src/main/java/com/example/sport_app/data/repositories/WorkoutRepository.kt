package com.example.sport_app.data.repositories

import com.example.sport_app.ApplicationController
import com.example.sport_app.data.models.WorkoutEntityModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.coroutines.launch

object WorkoutRepository {

    // Adaugă un workout în baza de date
    fun insertWorkout(workout: WorkoutEntityModel) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                ApplicationController.instance?.appDatabase?.workoutDAO?.insertWorkout(workout)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    // Returnează workout-urile pentru un anumit userId
    suspend fun getWorkoutsByUserId(userId: Long): List<WorkoutEntityModel> {
        return withContext(Dispatchers.IO) {
            try {
                ApplicationController.instance?.appDatabase?.workoutDAO?.getWorkoutsForUser(userId)
                    ?: emptyList()
            } catch (e: Exception) {
                e.printStackTrace()
                emptyList()
            }
        }
    }
}
