package org.nunocky.sample4.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [Topic::class], version = 1)
@TypeConverters(DataConverter::class)
abstract class TopicDatabase : RoomDatabase() {
    abstract fun getTopicDAO(): TopicDAO
}