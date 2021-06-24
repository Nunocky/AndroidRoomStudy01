package org.nunocky.sample2.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TopicTagRelation(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val topic_id: Long = 0,
    val tag_id: Long = 0
)