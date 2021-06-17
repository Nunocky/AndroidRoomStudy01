package org.nunocky.roomstudy01.database.room

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TopicDAO {
    @Insert
    fun insert(newTopic: Topic)

    @Update
    fun update(topic: Topic)

    @Delete
    fun delete(topic: Topic)

    @Query("select * from topics")
    fun getAll(): LiveData<List<Topic>>

    @Query("select * from topics where id=:id")
    fun getById(id: Int): Topic?
}