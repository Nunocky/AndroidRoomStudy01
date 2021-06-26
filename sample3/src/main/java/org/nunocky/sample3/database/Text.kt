package org.nunocky.sample3.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "texts")
data class Text(
    @PrimaryKey(autoGenerate = true) var id: Long,
    var text: String,
    var topic_id: Long = 0
)

