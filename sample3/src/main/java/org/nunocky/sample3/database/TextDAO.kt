package org.nunocky.sample3.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TextDAO {
    @Insert
    fun insert(text: Text): Long

    @Delete
    fun delete(text: Text)

    @Update
    fun update(text: Text)

    @Query("select * from texts")
    fun findAll(): LiveData<List<Text>>

    @Query("select * from texts where topic_id=:topicId")
    fun findAllRelated(topicId: Long): LiveData<List<Text>>
}