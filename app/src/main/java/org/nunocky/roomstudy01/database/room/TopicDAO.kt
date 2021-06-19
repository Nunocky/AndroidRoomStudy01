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

    @Query(
        """select * from topics 
                order by
                CASE WHEN :isAsc = 1 THEN createdAt END ASC,
                CASE WHEN :isAsc = 0 THEN createdAt END DESC """
    )
    fun getAllWithCreatedAt(isAsc: Boolean): LiveData<List<Topic>>

    @Query(
        """select * from topics 
                WHERE fav = 1
                order by
                CASE WHEN :isAsc = 1 THEN createdAt END ASC,
                CASE WHEN :isAsc = 0 THEN createdAt END DESC """
    )
    fun getAllFavoritesWithCreatedAt(isAsc: Boolean): LiveData<List<Topic>>

    @Query(
        """select * from topics 
                order by
                CASE WHEN :isAsc = 1 THEN updatedAt END ASC,
                CASE WHEN :isAsc = 0 THEN updatedAt END DESC """
    )
    fun getAllWithUpdatedAt(isAsc: Boolean): LiveData<List<Topic>>

    @Query(
        """select * from topics
                WHERE fav = 1
                order by
                CASE WHEN :isAsc = 1 THEN updatedAt END ASC,
                CASE WHEN :isAsc = 0 THEN updatedAt END DESC """
    )
    fun getAllFavoritesWithUpdatedAt(isAsc: Boolean): LiveData<List<Topic>>

    @Query("select * from topics where id=:id")
    fun getById(id: Int): Topic?
}