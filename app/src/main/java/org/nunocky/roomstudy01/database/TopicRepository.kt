package org.nunocky.roomstudy01.database

import android.content.Context
import androidx.room.Room
import org.nunocky.roomstudy01.database.room.AppDatabase
import org.nunocky.roomstudy01.database.room.TopicDAO

class TopicRepository {
    lateinit var topicDao: TopicDAO

    companion object {
        private var instance: TopicRepository? = null
        fun instance(): TopicRepository {
            if (instance == null) {
                instance = TopicRepository()
            }
            return requireNotNull(instance)
        }
    }

    fun init(context: Context) {
        setupDatabase(context)
    }

    private fun setupDatabase(context: Context) {
        val appDatabase = Room.databaseBuilder(context, AppDatabase::class.java, "appDatabase")
            .fallbackToDestructiveMigration()
            .build()

        topicDao = appDatabase.getTopicDao()
    }
}