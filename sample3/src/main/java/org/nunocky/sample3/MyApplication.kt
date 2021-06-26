package org.nunocky.sample3

import android.app.Application
import androidx.room.Room
import org.nunocky.sample3.database.AppDatabase

class MyApplication : Application() {
    //private val applicationScope = CoroutineScope(SupervisorJob() + Dispatchers.Main)

    lateinit var appDatabase: AppDatabase

    override fun onCreate() {
        super.onCreate()

        appDatabase =
            Room.databaseBuilder(this@MyApplication, AppDatabase::class.java, "appDatabase")
                .fallbackToDestructiveMigration()
                .build()
    }
}