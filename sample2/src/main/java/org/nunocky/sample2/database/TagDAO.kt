package org.nunocky.sample2.database

import androidx.room.*

@Dao
interface TagDAO {
    @Insert
    fun insert(tag: Tag): Long

    @Delete
    fun delete(tag: Tag)

    @Update
    fun update(tag: Tag)

    @Query("select * from tag where id=:id")
    fun findById(id: Int): Tag?

    @Query("select * from tag where id in (:ids)")
    fun findByIds(ids: IntArray): List<Tag>
}