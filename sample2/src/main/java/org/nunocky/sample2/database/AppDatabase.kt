package org.nunocky.sample2.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Tag::class, TagRelation::class, Topic::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getTopicDAO(): TopicDAO
    abstract fun getTagRelationDAO(): TagRelationDAO
    abstract fun getTagDAO(): TagDAO
}

