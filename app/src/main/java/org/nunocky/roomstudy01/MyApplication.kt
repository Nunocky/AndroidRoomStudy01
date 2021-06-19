package org.nunocky.roomstudy01

import android.app.Application
import androidx.room.Room
import org.nunocky.roomstudy01.database.TopicRepository
import org.nunocky.roomstudy01.database.room.AppDatabase

class MyApplication : Application() {
    val appDatabase: AppDatabase by lazy {
        Room.databaseBuilder(this@MyApplication, AppDatabase::class.java, "appDatabase")
            .fallbackToDestructiveMigration()
            .build()
    }

    val topicRepository: TopicRepository by lazy {
        val topicDAO = appDatabase.getTopicDao()
        TopicRepository(topicDAO)
    }
}