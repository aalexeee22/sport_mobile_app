package com.example.sport_app

import android.app.Application
import androidx.room.Room
import com.example.sport_app.data.AppDatabase

class ApplicationController : Application() {
    companion object {
        var instance: ApplicationController? = null
            private set
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
//            klass = appDatabase::class.java,
            klass = AppDatabase::class.java,
            name = "localRoomDatabase"
        )
            .fallbackToDestructiveMigration()
            .build()
    }
}