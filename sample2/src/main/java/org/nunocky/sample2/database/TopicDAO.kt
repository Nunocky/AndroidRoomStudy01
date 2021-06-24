package org.nunocky.sample2.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TopicDAO {
    @Query("select count(*) from topic")
    fun count(): Int

    @Insert
    fun insert(topic: Topic): Long

    @Delete
    fun delete(topic: Topic)

    @Update
    fun update(topic: Topic)

    @Query("select * from topic where id=:id")
    fun findById(id: Int): Topic?

    @Query(
        "select * from topic " +
                "where case when :onlyFavorites=1 then fav=1 else fav in (0, 1) end " +
                "and id in (select topic_id from topictagrelation where tag_id in (:tag_ids)) "
    )
    fun findByTagIds(tag_ids: IntArray, onlyFavorites: Boolean = false): LiveData<List<Topic>>
}