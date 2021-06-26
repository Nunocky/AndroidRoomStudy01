package org.nunocky.sample3.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TopicDAO {
    @Insert
    fun insert(topic: Topic): Long

    @Delete
    fun delete(topic: Topic)

    @Update
    fun update(topic: Topic)

    @Query("select * from topics")
    fun findAll(): LiveData<List<Topic>>

    @Query("select * from topics where id=:id")
    fun findTopicWithTextsWithId(id: Long): LiveData<TopicAndTexts>
}

