package org.nunocky.sample4.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TopicDAO {
    @Insert
    fun insert(topic: Topic)
    //fun delete(topic: Topic)
    //fun update(topic: Topic)

    @Query("select * from topic")
    fun findAll(): LiveData<List<Topic>>

    @Query("select count(*) from topic")
    fun count(): Int

    @Query("delete from topic")
    fun deleteAll()
}