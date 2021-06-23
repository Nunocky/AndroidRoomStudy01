package org.nunocky.sample2.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TagRelation(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val topic_id: Int,
    val tag_id: Int
)