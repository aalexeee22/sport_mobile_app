package com.example.sport_app.data.repositories

import at.favre.lib.crypto.bcrypt.BCrypt
import com.example.sport_app.ApplicationController
import com.example.sport_app.data.models.UserEntityModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


object UserRepository {
    fun insert(entityModel: UserEntityModel){
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val hashedPassword = hashPassword(entityModel.password)
                val userWithHashedPassword = entityModel.copy(password = hashedPassword)
                ApplicationController.instance?.appDatabase?.userDAO?.insert(userWithHashedPassword)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    suspend fun login(email: String, password: String): UserEntityModel? {
        val user = ApplicationController.instance?.appDatabase?.userDAO?.getUserByEmail(email)
        return if (user != null && verifyPassword(password, user.password)) user else null
    }

    private fun hashPassword(plainPassword: String): String {
        return BCrypt.withDefaults().hashToString(12, plainPassword.toCharArray())
    }

    private fun verifyPassword(plainPassword: String, hashedPassword: String): Boolean {
        return BCrypt.verifyer().verify(plainPassword.toCharArray(), hashedPassword).verified
    }

    suspend fun getUserByEmail(email: String): UserEntityModel? {
        return ApplicationController.instance?.appDatabase?.userDAO?.getUserByEmail(email)
    }
}
