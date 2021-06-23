package org.nunocky.sample2.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TopicDAO {
    @Query("select count(*) from topic")
    fun count(): Int

    @Insert
    fun insert(topic: Topic)

    @Delete
    fun delete(topic: Topic)

    @Update
    fun update(topic: Topic)

    @Query("select * from topic where id=:id")
    fun findById(id: Int): Topic?

    @Query(
        "select * from topic " +
                "where id in (select topic_id from tagrelation where tag_id in (:tag_ids))"
    )
    fun findByTagIds(tag_ids: IntArray): LiveData<List<Topic>>

}