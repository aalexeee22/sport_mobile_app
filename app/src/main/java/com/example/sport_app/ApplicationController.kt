package com.example.sport_app

import android.app.Application
import androidx.room.Room
import com.example.sport_app.data.AppDatabase
import com.example.sport_app.data.models.UserEntityModel

class ApplicationController : Application() {
    companion object {
        var instance: ApplicationController? = null
            private set

        var currentUser: UserEntityModel? = null // aici ținem userul logat
    }

    lateinit var appDatabase: AppDatabase


    override fun onCreate() {
        super.onCreate()
        instance = this
        initDatabase()
    }

    private fun initDatabase() {
        appDatabase = Room.databaseBuilder(
            context = this,
            klass = AppDatabase::class.java,
            name = "localDatabase"
        )
            .fallbackToDestructiveMigration()
            .build()
    }
}
