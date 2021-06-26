package org.nunocky.sample3.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Topic::class, Text::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getTopicDAO(): TopicDAO
    abstract fun getTextDAO(): TextDAO
}

