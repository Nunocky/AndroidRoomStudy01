package org.nunocky.sample2.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update

@Dao
interface TagRelationDAO {
    // CRUD
    @Insert
    fun insert(relation: TagRelation)

    @Delete
    fun delete(relation: TagRelation)

    @Update
    fun update(relation: TagRelation)

//    @Query("select * from tagrelation where tag_id=:tag")
//    fun findByTag(tag: Int)
}