package org.nunocky.sample2.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update

@Dao
interface TopicTagRelationDAO {
    // CRUD
    @Insert
    fun insert(relation: TopicTagRelation): Long

    @Delete
    fun delete(relation: TopicTagRelation)

    @Update
    fun update(relation: TopicTagRelation)

//    @Query("select * from tagrelation where tag_id=:tag")
//    fun findByTag(tag: Int)
}