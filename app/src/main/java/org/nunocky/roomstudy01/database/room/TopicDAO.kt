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
    fun getById(id: Int): LiveData<Topic>

    @Query(
        """SELECT * FROM topics
                WHERE 
                CASE WHEN :onlyFavorites=1 THEN fav=1 ELSE fav in (0, 1) END

                ORDER BY
                CASE WHEN :asc=1 THEN
                    CASE WHEN :createdAt=1 THEN createdAt
                          ELSE updatedAt END
                    END ASC,
                CASE WHEN :asc=0 THEN
                    CASE WHEN :createdAt=1 THEN createdAt
                          ELSE updatedAt END
                    END DESC
                """
    )
    fun findAll(createdAt: Boolean, asc: Boolean, onlyFavorites: Boolean): LiveData<List<Topic>>

}

