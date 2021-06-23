package org.nunocky.sample2.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Tag::class, TopicTagRelation::class, Topic::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getTagDAO(): TagDAO
    abstract fun getTopicDAO(): TopicDAO
    abstract fun getTopicTagRelationDAO(): TopicTagRelationDAO
}

