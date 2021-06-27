package org.nunocky.sample4

import android.app.Application
import androidx.room.Room
import org.nunocky.sample4.database.TopicDatabase

class MyApplication : Application() {
    val database: TopicDatabase by lazy {
        Room.databaseBuilder(this@MyApplication, TopicDatabase::class.java, "appDatabase")
            .fallbackToDestructiveMigration()
            .build()
    }
}