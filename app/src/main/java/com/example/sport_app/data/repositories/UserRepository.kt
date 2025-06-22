package com.example.sport_app.data.repositories

import com.example.sport_app.ApplicationController
import com.example.sport_app.data.models.UserEntityModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

object UserRepository {
    fun insert(entityModel: UserEntityModel){
        CoroutineScope(Dispatchers.IO).launch {
            ApplicationController.instance?.appDatabase?.userDAO?.insert(entityModel)
        }
    }
}
